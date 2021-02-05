package com.llw.goodweather.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.MemoryFile;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.msc.util.FileUtil;
import com.iflytek.cloud.msc.util.log.DebugLog;

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
     * Toast提示
     *
     * @param msg
     */
    private static void showTip(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }


}
