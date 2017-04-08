package br.com.zapimoveis.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class Properties {

    @SerializedName("Imoveis")
    private List<Property> properties;

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
