package br.com.zapimoveis.app.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import br.com.zapimoveis.app.BR;
import br.com.zapimoveis.app.R;
import br.com.zapimoveis.app.dao.DataManager;
import br.com.zapimoveis.app.model.Field;
import br.com.zapimoveis.app.model.Filter;
import br.com.zapimoveis.app.model.Property;
import br.com.zapimoveis.app.model.Result;
import br.com.zapimoveis.app.network.ConsumerService;
import br.com.zapimoveis.app.view.PropertyDetailActivity;
import br.com.zapimoveis.app.view.adapter.RecyclerBindingAdapter;
import br.com.zapimoveis.app.view.component.RecyclerConfiguration;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class MainViewModel implements ConsumerService.OnTaskCompleted<Result>, RecyclerBindingAdapter.OnItemClickListener<Property> {

    private static final String TAG = MainViewModel.class.getSimpleName();
    public RecyclerConfiguration recyclerConfiguration;
    private RecyclerBindingAdapter<Property> adapter;
    private final ConsumerService consumerService;
    private RealmResults<Property> properties;
    private ArrayList<Filter> filters;
    private NumberFormat currency;
    private Context context;
    public Field field;

    @BindView(R.id.swipe_container) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.filter_recycler_view) RecyclerView filterRecyclerView;
    @BindView(R.id.seek_bar) AppCompatSeekBar seekBar;

    public MainViewModel(Context context, View view) {
        this.context = context;
        this.field = new Field();
        this.currency = DecimalFormat.getCurrencyInstance();
        this.recyclerConfiguration = new RecyclerConfiguration();

        ButterKnife.bind(this, view);

        consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getProperties(-1);

        initSwipeLayout();
        initRecycler();
        initFilter();
    }

    /**
     * Inicializa o componente seekbar com os dados ja carregados no banco
     */
    private void initSeekBar(){

        final long maxValue = properties.where().max("salesPrice").longValue();
        final long minValue = properties.where().min("salesPrice").longValue();

        seekBar.setMax((int) maxValue);
        seekBar.setProgress((int) minValue);

        field.strField.set(currency.format(minValue / 100) + " até " + currency.format(maxValue / 100));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                field.strField.set(currency.format(progress / 100) + "até " + currency.format(maxValue / 100));
                properties = DataManager.selectBetween(Property.class, "salesPrice", progress, (int) maxValue);
                adapter.setList(properties);
                refreshFilter();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * Inicializa o componente swipelayout
     */
    private void initSwipeLayout(){
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFilter();
                consumerService.getProperties(-1);
            }
        });
    }

    /**
     * Inicializa a recyclerview
     */
    private void initRecycler() {
        adapter = getAdapter();
        adapter.setOnItemClickListener(this);

        recyclerConfiguration.setLayoutManager(new LinearLayoutManager(context));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

    /**
     * Inicializa o filtro com dados pre-cadastrados
     */
    private void initFilter(){
        filters = new ArrayList<>();

        filters.add(new Filter("Quartos", "bedrooms"));
        filters.add(new Filter("Banheiros", "suites"));
        filters.add(new Filter("Garagem", "parking"));
        filters.add(new Filter("Área Total", "totalArea"));
        filters.add(new Filter("Menor Preço", "salesPrice"));
        filters.add(new Filter("Maior Preço", "salesPrice"));

        RecyclerBindingAdapter<Filter> filterAdapter = new RecyclerBindingAdapter<>(R.layout.item_filter_list, BR.filter, filters);
        filterAdapter.setOnItemClickListener(new RecyclerBindingAdapter.OnItemClickListener<Filter>() {
            @Override
            public void onItemClick(int position, View view, Filter item) {
                refreshFilter();
                item.setSelected(true);

                if (item.getName().equals("Menor Preço"))
                    adapter.setList(properties.where().findAllSorted(item.getField(), Sort.ASCENDING));
                else
                    adapter.setList(properties.where().findAllSorted(item.getField(), Sort.DESCENDING));
            }
        });

        filterRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        filterRecyclerView.setItemAnimator(new DefaultItemAnimator());
        filterRecyclerView.setAdapter(filterAdapter);
    }

    /**
     * Inicializa um adapter com uma lista vaiza, para que depois
     * de terminar o request ela seja renovada com dados vindos
     * do servidor
     * @return
     */
    private RecyclerBindingAdapter<Property> getAdapter() {
        return new RecyclerBindingAdapter<>(R.layout.item_property_list, br.com.zapimoveis.app.BR.property, new ArrayList<Property>());
    }

    /**
     * Sucesso do request
     * @param result
     * @param code
     * @param requestCode
     */
    @Override
    public void onSuccess(Result result, int code, int requestCode) {

        if (code != 200) return;

        if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);

        DataManager.deleteAll(Property.class);
        DataManager.save(result.getProperties());

        properties = DataManager.selectAll(Property.class);

        adapter.setList(properties);

        initSeekBar();
    }

    @Override
    public void onFailure(Throwable error, int requestCode) {
        Log.e(TAG, "onFailure: ", error);
    }

    @Override
    public void onItemClick(int position, View view, Property item) {
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((AppCompatActivity) context, view.findViewById(R.id.picture), "picture").toBundle();
        context.startActivity(new Intent(context, PropertyDetailActivity.class).putExtra("extra", item.getCodProperty()), bundle);
    }

    /**
     * Atualiza o filtro, desmarcando todos os itens selecionados
     */
    private void refreshFilter(){
        for (Filter filter : filters) {
            filter.setSelected(false);
        }
    }
}
