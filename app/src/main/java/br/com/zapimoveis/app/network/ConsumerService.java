package br.com.zapimoveis.app.network;

import br.com.zapimoveis.app.BuildConfig;
import br.com.zapimoveis.app.model.Result;
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

        void onSuccess(T result, int code, int requestCode);

        void onFailure(Throwable error, int requestCode);
    }

    public ConsumerService() {
        service = ServiceGenerator.createService(BuildConfig.DEBUG, BuildConfig.URL, IConsumerService.class);
    }

    public void setOnTaskCompleted(OnTaskCompleted onTaskCompleted) {
        listener = onTaskCompleted;
    }

    public void getProperties(final int requestCode) {
        service.getProperties().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                listener.onSuccess(response.body(), response.code(), requestCode);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {
                listener.onFailure(throwable, requestCode);
            }
        });
    }

    public void getDetailById(long propertyId, final int requestCode) {
        service.getDetailById(propertyId).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                listener.onSuccess(response.body(), response.code(), requestCode);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {
                listener.onFailure(throwable, requestCode);
            }
        });
    }

}
