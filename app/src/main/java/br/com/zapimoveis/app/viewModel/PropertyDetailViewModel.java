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
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mypopsy.maps.StaticMap;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import br.com.zapimoveis.app.R;
import br.com.zapimoveis.app.dao.DataManager;
import br.com.zapimoveis.app.model.Detail;
import br.com.zapimoveis.app.model.Field;
import br.com.zapimoveis.app.model.Property;
import br.com.zapimoveis.app.model.Result;
import br.com.zapimoveis.app.network.ConsumerService;
import br.com.zapimoveis.app.view.adapter.CustomPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by gustavoterras on 08/04/17.
 */

public class PropertyDetailViewModel implements ConsumerService.OnTaskCompleted<Result>{

    private static final String TAG = PropertyDetailViewModel.class.getSimpleName();
    private CustomPagerAdapter adapter;
    private Address location;
    public Property property;
    private Context context;
    public Detail detail;
    public Field field;

    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.indicator) CircleIndicator indicator;
    @BindView(R.id.img_static_map) ImageView imgStaticMap;
    @BindView(R.id.scroll_view) NestedScrollView scrollView;
    @BindView(R.id.list_view_area_common) ListView listViewAreaCommon;
    @BindView(R.id.list_view_characteristics) ListView listViewCharacteristics;

    public PropertyDetailViewModel(Context context, View view, long propertyId) {
        this.property = DataManager.selectById(propertyId, Property.class);
        this.detail = new Detail();
        this.field = new Field();
        this.context = context;

        ButterKnife.bind(this, view);

        initActionbar();
        initViewPager();
        initScrollView();
        intStaticMapView();

        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getDetailById(propertyId, -1);
    }

    /**
     * Oculta a actionBar
     */
    private void initActionbar() {
        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    /**
     * Inicializa o componente scrollview para capturar o evento
     * de change, para manipular o exibição do floatactionbutton
     */
    private void initScrollView() {
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY)
                    fab.hide();
                else
                    fab.show();
            }
        });
    }

    /**
     * Inicializa o viewpager
     */
    private void initViewPager(){
        adapter = new CustomPagerAdapter(context, Collections.singletonList(property.getUrlImage()));
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }

    /**
     * Inicializa o mapa statico, tenta pegar a latitude e
     * longitude do imovel com base no endereço cadastrado
     * para aplicar no mapa statico
     */
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

    /**
     * Metodo para chamar opções de navegaçào via mapas
     * @param v
     */
    public void sharedLocation(View v) {
        String uri;
        if (location == null)
            uri = "geo: 0,0?q=" + Uri.encode(property.getAddress().toGeo());
        else
            uri = "geo: 0,0?q=" + location.getLatitude() + "," + location.getLongitude();

        context.startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
    }

    /**
     * Metodo para acionar uma ligação
     * @param v
     */
    public void doCall(View v) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "5199999999"));
            context.startActivity(intent);
        }
    }

    @Override
    public void onSuccess(Result result, int code, int requestCode) {

        if (code == 200) {

            field.bolField.set(true);

            this.detail.setObservation(result.getDetail().getObservation());

            adapter.setList(result.getDetail().getPictures());
            indicator.setViewPager(viewPager);

            field.bolFieldCharacteristics.set(result.getDetail().getCharacteristics() != null && !result.getDetail().getCharacteristics().isEmpty());
            listViewCharacteristics.setAdapter(new ArrayAdapter<>(context, R.layout.item_content_list, R.id.text1, result.getDetail().getCharacteristics()));
            getTotalHeightTofListView(listViewCharacteristics);


            field.bolFieldAreaCommon.set(result.getDetail().getCommonCharacteristics() != null && !result.getDetail().getCommonCharacteristics().isEmpty());
            listViewAreaCommon.setAdapter(new ArrayAdapter<>(context, R.layout.item_content_list, R.id.text1, result.getDetail().getCommonCharacteristics()));
            getTotalHeightTofListView(listViewAreaCommon);

        }

    }

    @Override
    public void onFailure(Throwable error, int requestCode) {
        Log.e(TAG, "onFailure: ", error);
    }

    /**
     * Metodo para setar dinamicamente o tamanho da listview
     * com base na quantide de itens do adapter e o tamanho
     * da view
     * @param listView
     */
    private void getTotalHeightTofListView(ListView listView) {

        ListAdapter mAdapter = listView.getAdapter();

        int totalHeight = 0;

        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);
            mView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += mView.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() -1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }
}
