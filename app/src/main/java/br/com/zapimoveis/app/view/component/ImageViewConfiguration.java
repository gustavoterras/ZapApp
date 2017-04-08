package br.com.zapimoveis.app.view.component;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImageViewConfiguration {

    @BindingAdapter("app:srcVector")
    public static void setSrcVector(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }
}