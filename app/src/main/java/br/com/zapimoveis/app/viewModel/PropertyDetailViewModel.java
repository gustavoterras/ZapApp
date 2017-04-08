package br.com.zapimoveis.app.viewModel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mypopsy.maps.StaticMap;

import java.io.IOException;
import java.util.List;

import br.com.zapimoveis.app.R;
import br.com.zapimoveis.app.dao.DataManager;
import br.com.zapimoveis.app.model.Property;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gustavoterras on 08/04/17.
 */

public class PropertyDetailViewModel {

    private static final String TAG = PropertyDetailViewModel.class.getSimpleName();
    private Context context;
    public Property property;
    private Address location;

    @BindView(R.id.scroll_view) NestedScrollView scrollView;
    @BindView(R.id.img_static_map) ImageView imgStaticMap;
    @BindView(R.id.fab) FloatingActionButton fab;

    public PropertyDetailViewModel(Context context, View view, long propertyId) {
        this.context = context;
        this.property = DataManager.selectById(propertyId, Property.class);

        ButterKnife.bind(this, view);

        initActionbar();
        initScrollView();
        intStaticMapView();
    }

    private void initActionbar() {
        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    private void initScrollView() {
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }
        });
    }

    private void intStaticMapView() {

        Geocoder coder = new Geocoder(context);
        List<Address> address;

        try {
            address = coder.getFromLocationName(property.getAddress().toGeo(), 5);
            if (address == null) return;
        } catch (IOException e) {
            Log.e(TAG, "getLocationFromAddress: ", e);
            return;
        }

        location = address.get(0);

        StaticMap map = new StaticMap()
                .center(location.getLatitude(), location.getLongitude())
                .size(250, 250)
                .zoom(16)
                .marker(location.getLatitude(), location.getLongitude());

        Glide.with(imgStaticMap.getContext()).load(Uri.decode(map.toString())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imgStaticMap.setImageBitmap(resource);
            }
        });
    }

    public void sharedLocation(View view) {
        String uri;
        if (location == null)
            uri = "geo: 0,0?q=" + Uri.encode(property.getAddress().toGeo());
        else
            uri = "geo: 0,0?q=" + location.getLatitude() + "," + location.getLongitude();

        context.startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
    }

    public void doCall(View view) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "99999999"));
            context.startActivity(intent);
        }
    }

}
