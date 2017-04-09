package br.com.zapimoveis.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class Result {

    @SerializedName("Imoveis")
    private List<Property> properties;

    @SerializedName("Imovel")
    private Detail detail;

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
