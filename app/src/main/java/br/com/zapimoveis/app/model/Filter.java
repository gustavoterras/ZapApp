package br.com.zapimoveis.app.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import br.com.zapimoveis.app.BR;

/**
 * Created by gustavoterras on 06/04/17.
 */
public class Filter extends BaseObservable {

    private String name;
    private String field;
    private boolean selected = false;

    public Filter(String name, String field) {
        this.name = name;
        this.field = field;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }

    @Bindable
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
        notifyPropertyChanged(BR.field);
    }
}