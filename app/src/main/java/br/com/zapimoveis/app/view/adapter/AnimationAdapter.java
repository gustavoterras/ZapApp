package br.com.zapimoveis.app.view.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class AnimationAdapter {

    @BindingAdapter("app:anim")
    public static void animationView(View view, Animation animation){
        view.startAnimation(animation);
    }

}
