package com.llw.goodweather.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.llw.goodweather.R;
import com.llw.goodweather.utils.APKVersionInfoUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.bean.AppVersion;
import com.llw.mvplibrary.utils.SizeUtils;
import com.llw.mvplibrary.view.dialog.AlertDialog;

import org.litepal.LitePal;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于 Good Weather
 */
public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lay_app_version)
    LinearLayout layAppVersion;
    @BindView(R.id.tv_version)
    TextView tvVersion;//版本
    @BindView(R.id.tv_blog)
    TextView tvBlog;//博客
    @BindView(R.id.tv_code)
    TextView tvCode;//源码
    @BindView(R.id.tv_copy_email)
    TextView tvCopyEmail;//复制邮箱
    @BindView(R.id.tv_author)
    TextView tvAuthor;//作者
    @BindView(R.id.v_red)
    View vRed;//红点
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private String updateUrl = null;
    private String updateLog = null;
    private boolean is_update = false;
    private AppVersion appVersion;
    private static String path = "/sdcard/GoodWeatherAPK/GoodWeather.apk";

    @Override
    public void initData(Bundle savedInstanceState) {
        Back(toolbar);
        StatusBarUtil.setStatusBarColor(context, R.color.about_bg_color);//白色状态栏
        tvCopyEmail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvCopyEmail.getPaint().setAntiAlias(true);//抗锯齿

        tvVersion.setText(APKVersionInfoUtils.getVerName(context));

        appVersion = LitePal.find(AppVersion.class, 1);
        updateLog = appVersion.getChangelog();

        if (!appVersion.getVersionShort().equals(APKVersionInfoUtils.getVerName(context))) {//提示更新
            is_update = true;
            vRed.setVisibility(View.VISIBLE);//显示红点
        } else {
            vRed.setVisibility(View.GONE);//隐藏红点
            is_update = false;
        }

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }


    @OnClick({R.id.lay_app_version, R.id.tv_blog, R.id.tv_code, R.id.tv_copy_email, R.id.tv_author})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_app_version://版本更新
                if (is_update) {
                    showUpdateAppDialog(updateUrl, updateLog);
                } else {
                    ToastUtils.showShortToast(context, "当前已是最新版本");
                }
                break;
            case R.id.tv_blog://博客地址
                jumpUrl("https://blog.csdn.net/qq_38436214/category_9880722.html");
                break;
            case R.id.tv_code://源码地址
                jumpUrl("https://github.com/lilongweidev/GoodWeather");
                break;
            case R.id.tv_copy_email://复制邮箱
                myClipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                myClip = ClipData.newPlainText("text", "lonelyholiday@qq.com");
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShortToast(context, "邮箱已复制");
                break;
            case R.id.tv_author:
                ToastUtils.showShortToast(context, "你为啥要点我呢？");
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
                    startActivity(new Intent(Intent.ACTION_VIEW, (Uri.parse(downloadUrl)))
                            .addCategory(Intent.CATEGORY_BROWSABLE)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                });
        updateAppDialog = builder.create();
        updateAppDialog.show();
    }

}
