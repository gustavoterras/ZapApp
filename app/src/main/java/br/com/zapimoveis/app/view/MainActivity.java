package br.com.zapimoveis.app.view;

import android.animation.Animator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import br.com.zapimoveis.app.R;
import br.com.zapimoveis.app.databinding.ActivityMainBinding;
import br.com.zapimoveis.app.viewModel.MainViewModel;

/**
 * Created by gustavoterras on 06/04/17.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // faz o bind da view com a viewModel
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new MainViewModel(this, findViewById(android.R.id.content)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // createCircularReveal animation na exibição do menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final View myView = findViewById(R.id.filter_content_layout);
        final int visibility = myView.getVisibility();

        int x = myView.getRight();
        int y = myView.getTop();

        int radius = (int) Math.hypot(myView.getWidth(), myView.getHeight());

        Animator animator;
        if (visibility == View.VISIBLE)
            animator = ViewAnimationUtils.createCircularReveal(myView, x, y, radius, 0);
        else
            animator = ViewAnimationUtils.createCircularReveal(myView, x, y, 0, radius);

        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1000);

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (visibility == View.GONE)
                    myView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (visibility == View.VISIBLE)
                    myView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.start();

        return true;
    }
}
