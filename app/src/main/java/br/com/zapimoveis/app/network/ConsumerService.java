package br.com.zapimoveis.app.network;

import br.com.zapimoveis.app.model.Properties;
import br.com.zapimoveis.app.model.PropertyDetail;
import br.com.zapimoveis.app.BuildConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gustavoterras on 06/04/17.
 */

@SuppressWarnings("unchecked")
public class ConsumerService {

    private OnTaskCompleted listener;
    private IConsumerService service;

    public interface OnTaskCompleted<T> {

        void onSuccess(T response,int code, int requestCode);

        void onFailure(Throwable error, int requestCode);
    }

    public ConsumerService() {
        service = ServiceGenerator.createService(BuildConfig.DEBUG, BuildConfig.URL, IConsumerService.class);
    }

    public void setOnTaskCompleted(OnTaskCompleted onTaskCompleted) {
        listener = onTaskCompleted;
    }

    public void getProperties(final int requestCode) {
        service.getProperties().enqueue(new Callback<Properties>() {
            @Override
            public void onResponse(Call<Properties> call, Response<Properties> response) {
                listener.onSuccess(response.body(), response.code(), requestCode);
            }

            @Override
            public void onFailure(Call<Properties> call, Throwable throwable) {
                listener.onFailure(throwable, requestCode);
            }
        });
    }

    public void getPropertyById(int propertyId, final int requestCode) {
        service.getPropertyById(propertyId).enqueue(new Callback<PropertyDetail>() {
            @Override
            public void onResponse(Call<PropertyDetail> call, Response<PropertyDetail> response) {
                listener.onSuccess(response.body(), response.code(), requestCode);
            }

            @Override
            public void onFailure(Call<PropertyDetail> call, Throwable throwable) {
                listener.onFailure(throwable, requestCode);
            }
        });
    }

}
