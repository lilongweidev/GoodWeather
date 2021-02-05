package com.llw.goodweather.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import static com.bumptech.glide.Glide.*;

/**
 * Glide工具类
 *
 * @author llw
 */
public class GlideUtil {

    private static final String TAG = "GlideUtil";
    //上下文
    private static Context context;

    private static GlideCallback mGlideCallback;


    //图片加载网络监听
    private static RequestListener<Drawable> requestListener = new RequestListener<Drawable>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            Log.d(TAG, "网络访问失败，请检查是否开始网络或者增加http的访问许可");
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            Log.d(TAG, "网络访问成功，可以显示图片");
            return false;
        }
    };

    /**
     * 初始化
     * @param context
     */
    public static void init(Context context) {
        GlideUtil.context = context;

    }

    /**
     * 获取ImageViewTarget
     *
     * @param imageView
     * @return
     */
    private static ImageViewTarget<Drawable> getImageViewTarget(ImageView imageView,GlideCallback glideCallback) {

        return new ImageViewTarget<Drawable>(imageView) {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
                Log.d(TAG, "开始加载图片");
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                Log.d(TAG, "加载图片失败");
            }

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                super.onResourceReady(resource, transition);
                // 图片加载完成
                imageView.setImageDrawable(resource);
                Log.d(TAG, "加载图片完成");
                //接口回调
                if(glideCallback!=null){
                    glideCallback.onResourceComplete();
                }
            }


            @Override
            protected void setResource(@Nullable Drawable resource) {

            }
        };
    }

    /**
     * 获取ImageViewTarget
     *
     * @param imageView
     * @return
     */
    private static ImageViewTarget<Drawable> getImageViewTarget(ImageView imageView) {

        return new ImageViewTarget<Drawable>(imageView) {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
                Log.d(TAG, "开始加载图片");
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                Log.d(TAG, "加载图片失败");
            }

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                super.onResourceReady(resource, transition);
                // 图片加载完成
                imageView.setImageDrawable(resource);
                Log.d(TAG, "加载图片完成");
            }


            @Override
            protected void setResource(@Nullable Drawable resource) {

            }
        };
    }


    /**
     * 显示网络Url图片
     *
     * @param url
     * @param imageView
     */
    public static void loadImg(String url, ImageView imageView) {
        with(context).load(url).into(imageView);
    }

    /**
     * 显示资源图片
     *
     * @param recourseId 资源图片
     * @param imageView
     */
    public static void loadImg(Integer recourseId, ImageView imageView) {
        with(context).load(recourseId).into(imageView);
    }

    /**
     * 显示bitmap图片
     *
     * @param bitmap
     * @param imageView
     */
    public static void loadImg(Bitmap bitmap, ImageView imageView) {
        with(context).load(bitmap).into(imageView);
    }

    /**
     * 显示drawable图片
     *
     * @param drawable
     * @param imageView
     */
    public static void loadImg(Drawable drawable, ImageView imageView) {
        with(context).load(drawable).into(imageView);
    }

    /**
     * 显示网络Url图片 附带加载网络监听和设置资源监听
     * @param url  网络图片url
     * @param imageView 图片控件
     * @param needNetListener 是否需要网络监听
     * @param needResourceListener 是否需要设置资源监听
     */
    public static void loadImgListener(String url, ImageView imageView,
                                       boolean needNetListener, boolean needResourceListener) {
        if (needResourceListener) {
            Glide.with(context)
                    .load(url)
                    .listener(needNetListener ? requestListener : null)
                    .into(getImageViewTarget(imageView));
        } else {
            Glide.with(context)
                    .load(url)
                    .listener(needNetListener ? requestListener : null)
                    .into(imageView);
        }
    }

    /**
     * 显示网络Url图片 附带加载网络监听和设置资源监听
     * @param url  网络图片url
     * @param imageView 图片控件
     * @param needNetListener 是否需要网络监听
     * @param needResourceListener 是否需要设置资源监听
     */
    public static void loadImgListener(String url, ImageView imageView,
                                       boolean needNetListener, boolean needResourceListener,
                                       GlideCallback glideCallback
                                       ) {
        mGlideCallback = glideCallback;
        if (needResourceListener) {
            Glide.with(context)
                    .load(url)
                    .listener(needNetListener ? requestListener : null)
                    .into(getImageViewTarget(imageView,mGlideCallback));
        } else {
            Glide.with(context)
                    .load(url)
                    .listener(needNetListener ? requestListener : null)
                    .into(imageView);
        }
    }

    public interface GlideCallback{
        /**
         * 图片资源加载完成
         */
        void onResourceComplete();
    }
}
