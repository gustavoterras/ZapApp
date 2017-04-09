package br.com.zapimoveis.app.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by gustavoterras on 06/04/17.
 */
public class Field {
    public final ObservableField<String> strField = new ObservableField<>();
    public final ObservableBoolean bolField = new ObservableBoolean();
    public final ObservableBoolean bolFieldCharacteristics = new ObservableBoolean();
    public final ObservableBoolean bolFieldAreaCommon = new ObservableBoolean();
}