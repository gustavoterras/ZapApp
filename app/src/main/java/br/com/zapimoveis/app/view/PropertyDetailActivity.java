package br.com.zapimoveis.app.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AnimationUtils;

import br.com.zapimoveis.app.R;
import br.com.zapimoveis.app.databinding.ActivityDetailBinding;
import br.com.zapimoveis.app.viewModel.PropertyDetailViewModel;

public class PropertyDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long propertyId = getIntent().getLongExtra("extra", -1);

        if (propertyId < 0) finish();

        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setViewModel(new PropertyDetailViewModel(this, findViewById(android.R.id.content), propertyId));

        Slide slide = new Slide(Gravity.BOTTOM);
        slide.addTarget(R.id.comments_layout);
        slide.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.accelerate_decelerate));
        slide.setDuration(getResources().getInteger(android.R.integer.config_longAnimTime));
        getWindow().setEnterTransition(slide);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
