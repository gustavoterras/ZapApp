package br.com.zapimoveis.app.network;

import com.google.gson.JsonObject;

import java.util.Map;

import br.com.zapimoveis.app.model.Result;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by gustavoterras on 06/04/17.
 */

public interface IConsumerService {

    @GET("/imoveis")
    Call<Result> getProperties();

    @GET("/imoveis/{id}")
    Call<Result> getDetailById(@Path("id") long id);

    @POST("/imoveis/contato")
    Call<JsonObject> doContact(@Body Map body);

}
