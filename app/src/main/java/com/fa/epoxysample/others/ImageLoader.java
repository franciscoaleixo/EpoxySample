package com.fa.epoxysample.others;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import java.lang.ref.WeakReference;

/**
 * Created by neuro on 24-05-2016.
 */
public class ImageLoader {

  private static final String TAG = ImageLoader.class.getName();

  private final WeakReference<Context> weakContext;
  private final Resources resources;
  private final WindowManager windowManager;

  private ImageLoader(Context context) {
    this.weakContext = new WeakReference<>(context);
    this.resources = context.getResources();
    this.windowManager = ((WindowManager) context.getSystemService(Service.WINDOW_SERVICE));
  }

  public static ImageLoader with(Context context) {
    return new ImageLoader(context);
  }

  public Target<Drawable> load(String url, @DrawableRes int loadingPlaceHolder,
      ImageView imageView) {
    Context context = weakContext.get();
    if (context != null) {
      return Glide.with(context)
          .load(url)
          .placeholder(loadingPlaceHolder)
          .transition(DrawableTransitionOptions.withCrossFade())
          .into(imageView);
    } else {
      Log.e(TAG, "::load() Context is null");
    }
    return null;
  }

  @SuppressLint("CheckResult") @NonNull private RequestOptions getRequestOptions() {
    RequestOptions requestOptions = new RequestOptions();
    DecodeFormat decodeFormat;
    if (Build.VERSION.SDK_INT >= 26) {
      decodeFormat = DecodeFormat.PREFER_ARGB_8888;
      requestOptions.disallowHardwareConfig();
    } else {
      decodeFormat = DecodeFormat.PREFER_RGB_565;
    }
    return requestOptions.format(decodeFormat)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
  }
}
