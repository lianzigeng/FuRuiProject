package com.furuiproject.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 *
 * 自定义Toast  为了防止 排队重复Toast  仿写Toasty
 */

public class CustomToast {
    private static CustomToast sMyToast = null;
    private static Toast sToast = null;

//    private static final Handler UIHANDLER = new Handler(Looper.getMainLooper());  暂时 没有遇到 Toast 不能再子线程中Toast

    @ColorInt
    private static int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
    @ColorInt
    private static int ERROR_COLOR = Color.parseColor("#D50000");
    @ColorInt
    private static int INFO_COLOR = Color.parseColor("#3F51B5");
    @ColorInt
    private static int SUCCESS_COLOR = Color.parseColor("#388E3C");
    @ColorInt
    private static int WARNING_COLOR = Color.parseColor("#FFA900");


    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;
    private static int textSize = 16; // in SP

    private static LayoutInflater layoutInflater = null;

    private CustomToast(Context context) {
        if (sToast == null) {
            sToast = new Toast(context);
        }
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(context);
        }
    }

    /**
     * @Des 获取单例
     */
    public static CustomToast getInstance(Context context) {
        if (sMyToast == null) {
            sMyToast = new CustomToast(context.getApplicationContext());
        }
        return sMyToast;
    }

    /**
     * @Des info 吐司
     */
    public void toastInfo(Context context, String message) {
        toastInfo(context, message, Toast.LENGTH_SHORT);
    }

    public void toastInfo(Context context, String message, int toastTime) {

        custom(context, message, getDrawable(context, getImageId(context, "ic_info_outline_white_48dp")),
                INFO_COLOR, toastTime);
    }

    /**
     * @Des success 吐司
     */
    public void toastSuccess(Context context, String message) {
        toastSuccess(context, message, Toast.LENGTH_SHORT);
    }

    public void toastSuccess(Context context, String message, int toastTime) {

        custom(context, message, getDrawable(context, getImageId(context, "ic_check_white_48dp")),
                SUCCESS_COLOR, toastTime);
    }

    /**
     * @Des error 吐司
     */
    public void toastError(Context context, String message) {
        toastError(context, message, Toast.LENGTH_SHORT);
    }

    public void toastError(Context context, String message, int toastTime) {

        custom(context, message, getDrawable(context, getImageId(context, "ic_error_outline_white_48dp")),
                ERROR_COLOR, toastTime);
    }

    /**
     * @Des warning 吐司
     */
    public void toastWarning(Context context, String message) {
        toastWarning(context, message, Toast.LENGTH_SHORT);
    }

    public void toastWarning(Context context, String message, int toastTime) {
        custom(context, message, getDrawable(context, getImageId(context, "ic_info_outline_white_48dp")),
                WARNING_COLOR, toastTime);
    }

    private void custom(Context context, String message, Drawable icon, int tintColor, int duration) {
        if (sToast == null) {
            throw new IllegalArgumentException("sToast is  null ");
        }
        int layoutID = context.getResources().getIdentifier("toast_layout", "layout", context.getPackageName());
        View root = layoutInflater.inflate(layoutID, null);
        int toastImage = context.getResources().getIdentifier("image_toast", "id", context.getPackageName());
        ImageView imageView = root.findViewById(toastImage);
        int toastText = context.getResources().getIdentifier("text_toast", "id", context.getPackageName());
        TextView textView = root.findViewById(toastText);
        Drawable drawableFrame = tint9PatchDrawableFrame(context, tintColor);
        root.setBackground(drawableFrame);
        if (icon == null)
            throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
        Drawable imageBack = tintIcon(icon, DEFAULT_TEXT_COLOR);
        imageView.setBackground(imageBack);
        textView.setText(message);
        textView.setTextColor(DEFAULT_TEXT_COLOR);
        textView.setTypeface(currentTypeface);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        sToast.setDuration(duration);
        sToast.setGravity(Gravity.TOP, 0, 50);
        sToast.setView(root);
        sToast.show();

    }

    private Drawable tintIcon(@NonNull Drawable drawable, @ColorInt int tintColor) {
        drawable.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    private Drawable tint9PatchDrawableFrame(@NonNull Context context, @ColorInt int tintColor) {
        int toast_frame = getImageId(context, "toast_frame");
        NinePatchDrawable toastDrawable = (NinePatchDrawable) getDrawable(context,toast_frame);
        return tintIcon(toastDrawable, tintColor);
    }


    private Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(id);
        else
            return context.getResources().getDrawable(id, null);
    }

    private int getImageId(Context context, String idName) {
        int mipmapId = context.getResources().getIdentifier(idName, "mipmap", context.getPackageName());
        return mipmapId;
    }
}

