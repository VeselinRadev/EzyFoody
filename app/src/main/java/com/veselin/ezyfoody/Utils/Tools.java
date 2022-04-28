package com.veselin.ezyfoody.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.veselin.ezyfoody.R;

public class Tools {
    public static void setSystemBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public static int dpToPx(Context context, int i) {
        return Math.round(TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics()));
    }
    public static void displayImageOriginal(Context context, ImageView imageView, int i) {
        try {
            Glide.with(context).load(Integer.valueOf(i)).centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
        } catch (Exception unused) {
        }
    }
}
