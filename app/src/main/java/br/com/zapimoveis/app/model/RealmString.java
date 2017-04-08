package br.com.zapimoveis.app.model;

import io.realm.RealmObject;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class RealmString extends RealmObject {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}