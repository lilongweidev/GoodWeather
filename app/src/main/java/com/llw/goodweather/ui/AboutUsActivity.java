package com.llw.goodweather.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.llw.goodweather.R;
import com.llw.goodweather.utils.APKVersionInfoUtils;
import com.llw.goodweather.utils.StatusBarUtil;
import com.llw.goodweather.utils.ToastUtils;
import com.llw.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于 Good Weather
 */
public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    public void initData(Bundle savedInstanceState) {
        Back(toolbar);
        StatusBarUtil.setStatusBarColor(context, R.color.about_bg_color);//白色状态栏
        tvVersion.setText(APKVersionInfoUtils.getVerName(context));
        tvCopyEmail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvCopyEmail.getPaint().setAntiAlias(true);//抗锯齿
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }


    @OnClick({R.id.tv_blog, R.id.tv_code, R.id.tv_copy_email,R.id.tv_author})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                ToastUtils.showShortToast(context,"邮箱已复制");
                break;
            case R.id.tv_author:
                ToastUtils.showShortToast(context,"你为啥要点我呢？");
                break;
        }
    }

    /**
     * 跳转URL
     * @param url 地址
     */
    private void jumpUrl(String url){
        if(url!=null){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }else{
            ToastUtils.showShortToast(context,"未找到相关地址");
        }
    }
}
