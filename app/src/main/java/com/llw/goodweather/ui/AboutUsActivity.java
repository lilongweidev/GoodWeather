package com.llw.goodweather.ui;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;

import com.llw.goodweather.R;
import com.llw.goodweather.databinding.ActivityAboutUsBinding;
import com.llw.goodweather.utils.APKVersionInfoUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.base.vb.BaseVBActivity;
import com.llw.mvplibrary.bean.AppVersion;
import com.llw.mvplibrary.utils.SizeUtils;
import com.llw.mvplibrary.view.dialog.AlertDialog;

import org.litepal.LitePal;

import java.io.File;

/**
 * 关于 Good Weather
 *
 * @author llw
 */
public class AboutUsActivity extends BaseVBActivity<ActivityAboutUsBinding> implements View.OnClickListener {

    private String updateUrl = null;
    private String updateLog = null;
    private boolean is_update = false;
    /**
     * 博客地址
     */
    private final String CSDN_BLOG_URL = "https://blog.csdn.net/qq_38436214/category_9880722.html";
    /**
     * 源码地址
     */
    private final String GITHUB_URL = "https://github.com/lilongweidev/GoodWeather";

    @Override
    public void initData() {
        Back(binding.toolbar);
        //蓝色状态栏
        StatusBarUtil.setStatusBarColor(context, R.color.about_bg_color);
        //设置文字下划线
        binding.tvCopyEmail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //抗锯齿
        binding.tvCopyEmail.getPaint().setAntiAlias(true);
        binding.tvVersion.setText(APKVersionInfoUtils.getVerName(context));
        AppVersion appVersion = LitePal.find(AppVersion.class, 1);
        updateLog = appVersion.getChangelog();
        //提示更新
        if (!appVersion.getVersionShort().equals(APKVersionInfoUtils.getVerName(context))) {
            is_update = true;
            //显示红点
            binding.vRed.setVisibility(View.VISIBLE);
            updateUrl = appVersion.getInstall_url();
            updateLog = appVersion.getChangelog();
        } else {
            //隐藏红点
            binding.vRed.setVisibility(View.GONE);
            is_update = false;
        }
        //添加点击事件
        binding.layAppVersion.setOnClickListener(this);
        binding.tvBlog.setOnClickListener(this);
        binding.tvCode.setOnClickListener(this);
        binding.tvCopyEmail.setOnClickListener(this);
        binding.tvAuthor.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lay_app_version://版本更新
                if (is_update) {
                    showUpdateAppDialog(updateUrl, updateLog);
                } else {
                    ToastUtils.showShortToast(context, "当前已是最新版本");
                }
                break;
            case R.id.tv_blog://博客地址
                jumpUrl(CSDN_BLOG_URL);
                break;
            case R.id.tv_code://源码地址
                jumpUrl(GITHUB_URL);
                break;
            case R.id.tv_copy_email://复制邮箱
                ClipboardManager myClipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip = ClipData.newPlainText("text", "lonelyholiday@qq.com");
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShortToast(context, "邮箱已复制");
                break;
            case R.id.tv_author://作者
                ToastUtils.showShortToast(context, "你为啥要点我呢？");
                break;
            default:
                ToastUtils.showShortToast(context, "点你咋的！");
                break;
        }
    }

    /**
     * 跳转URL
     *
     * @param url 地址
     */
    private void jumpUrl(String url) {
        if (url != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } else {
            ToastUtils.showShortToast(context, "未找到相关地址");
        }
    }

    AlertDialog updateAppDialog = null;

    /**
     * 更新弹窗
     *
     * @param downloadUrl 下载地址
     * @param updateLog   更新内容
     */
    private void showUpdateAppDialog(String downloadUrl, String updateLog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setText(R.id.tv_update_info, updateLog)
                .setContentView(R.layout.dialog_update_app_tip)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 270), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tv_cancel, v -> {//取消
                    updateAppDialog.dismiss();
                }).setOnClickListener(R.id.tv_fast_update, v -> {//立即更新
                    ToastUtils.showShortToast(context, "正在后台下载，下载后会自动安装");
                    downloadApk(downloadUrl);
                    updateAppDialog.dismiss();
                });
        updateAppDialog = builder.create();
        updateAppDialog.show();
    }

    /**
     * 清除APK
     */
    public static File clearApk(String apkName) {
        File apkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), apkName);
        if (apkFile.exists()) {
            apkFile.delete();
        }
        return apkFile;
    }

    /**
     * 下载APK
     */
    private void downloadApk(String downloadUrl) {
        clearApk("GoodWeather.apk");
        //下载管理器 获取系统下载服务
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        //设置运行使用的网络类型，移动网络或者Wifi都可以
        request.setAllowedNetworkTypes(request.NETWORK_MOBILE | request.NETWORK_WIFI);
        //设置是否允许漫游
        request.setAllowedOverRoaming(true);
        //设置文件类型
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(downloadUrl));
        request.setMimeType(mimeString);
        //设置下载时或者下载完成时，通知栏是否显示
        request.setNotificationVisibility(request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("下载新版本");
        request.setVisibleInDownloadsUi(true);//下载UI
        //sdcard目录下的download文件夹
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "GoodWeather.apk");
        //将下载请求放入队列
        downloadManager.enqueue(request);
    }
}
