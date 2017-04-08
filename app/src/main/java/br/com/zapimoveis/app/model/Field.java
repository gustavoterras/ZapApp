package br.com.zapimoveis.app.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class Field {
    public final ObservableField<String> strField = new ObservableField<>();
    public final ObservableBoolean bolField = new ObservableBoolean();
    public final ObservableInt intField = new ObservableInt();
}