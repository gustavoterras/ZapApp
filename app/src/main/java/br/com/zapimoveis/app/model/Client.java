package br.com.zapimoveis.app.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class Client extends RealmObject {

    @SerializedName("CodCliente")
    private long codClient;

    @SerializedName("NomeFantasia")
    private String name;

    public long getCodClient() {
        return codClient;
    }

    public void setCodClient(long codClient) {
        this.codClient = codClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
