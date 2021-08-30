package com.llw.goodweather.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.os.MemoryFile;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.msc.util.FileUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.umeng.umcrash.UMCrash;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Vector;

/**
 * 语音工具类
 *
 * @author llw
 */
public class SpeechUtil {

    private static final String TAG = "SpeechUtil";

    private static Context mContext;

    // 语音合成对象
    public static SpeechSynthesizer mTts;

    //播放的文字
    private static String defaultText = "富强、明主、文明、和谐、自由、平等、公正、法制、爱国、敬业、诚信、友善。";


    // 引擎类型
    private static String mEngineType = SpeechConstant.TYPE_CLOUD;

    private static Vector<byte[]> container = new Vector<>();

    //内存文件
    private static MemoryFile memoryFile;
    //总大小
    public static volatile long mTotalSize = 0;


    // 默认发音人
    private static String voicer = "xiaoyan";
    //语速
    private static String speedValue = "50";
    //音调
    private static String pitchValue = "50";
    //音量
    private static String volumeValue = "50";

    private static TextView tvState;

    /****************语音识别********************/

    private static SpeechRecognizer mIat;// 语音听写对象
    private static RecognizerDialog mIatDialog;// 语音听写UI

    // 用HashMap存储听写结果
    private static HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    private static SharedPreferences mSharedPreferences;//缓存

    private static String language = "zh_cn";//识别语言

    private static String resultType = "json";//结果内容数据格式

    private static String dictationResults;//听写结果

    /**
     * 初始化监听。
     */
    private static InitListener mTtsInitListener = code -> {
        Log.i(TAG, "InitListener init() code = " + code);
        if (code != ErrorCode.SUCCESS) {
            Log.i(TAG, "初始化失败,错误码：" + code);
        } else {
            Log.i(TAG, "初始化成功");
        }
    };


    /**
     * 初始化
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context;
        // 初始化合成对象
        mTts = SpeechSynthesizer.createSynthesizer(mContext, mTtsInitListener);


        /***************  语音听写 *****************/
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(mContext, mInitListener);
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mSharedPreferences = mContext.getSharedPreferences("ASR",
                Activity.MODE_PRIVATE);
    }

    /**
     * 合成回调监听。
     */
    private static SynthesizerListener mTtsListener = new SynthesizerListener() {
        //开始播放
        @Override
        public void onSpeakBegin() {
            Log.i(TAG, "开始播放");
        }

        //暂停播放
        @Override
        public void onSpeakPaused() {
            Log.i(TAG, "暂停播放");
        }

        //继续播放
        @Override
        public void onSpeakResumed() {
            Log.i(TAG, "继续播放");
        }

        //合成进度
        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
            Log.i(TAG, "合成进度：" + percent + "%");
        }

        //播放进度
        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
            Log.i(TAG, "播放进度：" + percent + "%");
            tvState.setText("播报中");
        }

        //播放完成
        @Override
        public void onCompleted(SpeechError error) {
            tvState.setText("播报完成");
            if (error == null) {
                Log.i(TAG, "播放完成," + container.size());
                DebugLog.LogD("播放完成," + container.size());
                for (int i = 0; i < container.size(); i++) {
                    //写入文件
                    writeToFile(container.get(i));
                }
                //保存文件
                FileUtil.saveFile(memoryFile, mTotalSize, mContext.getExternalFilesDir(null) + "/1.pcm");
            } else {
                //异常信息
                showTip(error.getPlainDescription(true));
            }
            tvState.setText("");
        }

        //事件
        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            //	 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            //	 若使用本地能力，会话id为null
            if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
                Log.i(TAG, "session id =" + sid);
            }

            //当设置SpeechConstant.TTS_DATA_NOTIFY为1时，抛出buf数据
            if (SpeechEvent.EVENT_TTS_BUFFER == eventType) {
                byte[] buf = obj.getByteArray(SpeechEvent.KEY_EVENT_TTS_BUFFER);
                Log.i(TAG, "bufis =" + buf.length);
                container.add(buf);
            }
        }
    };

    /**
     * 写入文件
     */
    private static void writeToFile(byte[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        try {
            if (memoryFile == null) {
                Log.i(TAG, "memoryFile is null");
                String mFilepath = mContext.getExternalFilesDir(null) + "/1.pcm";
                memoryFile = new MemoryFile(mFilepath, 1920000);
                memoryFile.allowPurging(false);
            }
            memoryFile.writeBytes(data, 0, (int) mTotalSize, data.length);
            mTotalSize += data.length;
        } catch (Exception e) {
            UMCrash.generateCustomLog(e,"UmengException");
            e.printStackTrace();
        }
    }

    /**
     * 参数设置
     *
     * @return
     */
    private static void setParam() {
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        // 根据合成引擎设置相应参数
        if (mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
            //支持实时音频返回，仅在synthesizeToUri条件下支持
            mTts.setParameter(SpeechConstant.TTS_DATA_NOTIFY, "1");

            //获取缓存值
            voicer = SPUtils.getString(Constant.VOICE_NAME, "xiaoyan", mContext);
            speedValue = SPUtils.getString(Constant.SPEED, "50", mContext);
            pitchValue = SPUtils.getString(Constant.PITCH, "50", mContext);
            volumeValue = SPUtils.getString(Constant.VOLUME, "50", mContext);

            // 设置在线合成发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicer);
            //设置合成语速
            mTts.setParameter(SpeechConstant.SPEED, speedValue);
            //设置合成音调
            mTts.setParameter(SpeechConstant.PITCH, pitchValue);
            //设置合成音量
            mTts.setParameter(SpeechConstant.VOLUME, volumeValue);
        } else {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
            mTts.setParameter(SpeechConstant.VOICE_NAME, "");
        }
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "false");
        // 设置音频保存路径，保存音频格式支持pcm、wav
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "pcm");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, mContext.getExternalFilesDir(null) + "/msc/tts.pcm");
    }

    /**
     * 开始语音预报
     */
    public static void startVoiceBroadcast(String text, TextView textView) {
        tvState = textView;
        if (mTts == null) {
            showTip("创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化");
            return;
        }
        if (text == null || text.isEmpty()) {
            text = defaultText;
        }
        //设置参数
        setParam();
        //开始合成播放
        int code = mTts.startSpeaking(text, mTtsListener);
        if (code != ErrorCode.SUCCESS) {
            showTip("语音合成失败,错误码: " + code);
        }
    }

    /**
     * 初始化语音听写监听器
     */
    private static InitListener mInitListener = code -> {
        Log.d(TAG, "SpeechRecognizer init() code = " + code);
        if (code != ErrorCode.SUCCESS) {
            showTip("初始化失败，错误码：" + code + ",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
        }
    };


    /**
     * 听写UI监听器
     */
    private static RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        /**
         * 识别结果
         */
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {

            parsingResult(results);//结果数据解析
        }

        /**
         * 识别回调错误
         */
        @Override
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true));
        }

    };

    /**
     * 语音识别结果数据解析
     *
     * @param results
     */
    private static void parsingResult(RecognizerResult results) {
        //获取解析结果
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        dictationResults = resultBuffer.toString();//听写结果显示
        //回调
        mSpeechCallback.dictationResults(dictationResults);

        Log.d(TAG,dictationResults);
    }


    /**
     * 听写参数设置
     *
     * @return
     */
    public static void setDictationParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);
        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, resultType);

        if (language.equals("zh_cn")) {
            String lag = mSharedPreferences.getString("iat_language_preference",
                    "mandarin");
            Log.e(TAG, "language:" + language);// 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, lag);
        } else {

            mIat.setParameter(SpeechConstant.LANGUAGE, language);
        }
        Log.e(TAG, "last language:" + mIat.getParameter(SpeechConstant.LANGUAGE));

        //此处用于设置dialog中不显示错误码信息
        //mIat.setParameter("view_tips_plain","false");

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, mSharedPreferences.getString("iat_vadbos_preference", "4000"));

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, mSharedPreferences.getString("iat_vadeos_preference", "1000"));

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, mSharedPreferences.getString("iat_punc_preference", "1"));

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");
    }

    /**
     * 开始听写
     */
    public static void startDictation(Context context,SpeechCallback speechCallback){
        mSpeechCallback = speechCallback;
        if( null == mIat ){
            // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
            showTip( "创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化" );
            return;
        }

        mIatResults.clear();//清除数据
        setDictationParam(); // 设置参数
        mIatDialog = new RecognizerDialog(context, mInitListener);
        mIatDialog.setListener(mRecognizerDialogListener);//设置监听

        mIatDialog.show();// 显示对话框

        //获取字体所在的控件
        TextView tvLink = Objects.requireNonNull(mIatDialog.getWindow()).getDecorView().findViewWithTag("textlink");
        tvLink.setText(" ");
        tvLink.getPaint().setFlags(Paint.SUBPIXEL_TEXT_FLAG);//取消下划线
        tvLink.setEnabled(false);//禁用点击
    }

    /**
     * Toast提示
     *
     * @param msg
     */
    private static void showTip(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    //语音回调
    private static SpeechCallback mSpeechCallback;

    /**
     * 语音回调接口
     */
    public interface SpeechCallback {
        /**
         * 听写结果
         */
        void dictationResults(String cityName);
    }

}
