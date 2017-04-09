package br.com.zapimoveis.app.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.zapimoveis.app.BR;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class Detail extends BaseObservable {

    @SerializedName("Fotos")
    private List<String> pictures;

    @SerializedName("SubTipoOferta")
    private String offerSubType;

    @SerializedName("Observacao")
    private String observation;

    @SerializedName("Caracteristicas")
    private List<String> characteristics;

    @SerializedName("PrecoCondominio")
    private long townHousePrice;

    @SerializedName("SubtipoImovel")
    private String propertySubType;

    @SerializedName("CaracteristicasComum")
    private List<String> commonCharacteristics;

    @SerializedName("InformacoesComplementares")
    private String complementInfo;

    @Bindable
    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
        notifyPropertyChanged(BR.pictures);
    }

    @Bindable
    public String getOfferSubType() {
        return offerSubType;
    }

    public void setOfferSubType(String offerSubType) {
        this.offerSubType = offerSubType;
        notifyPropertyChanged(BR.offerSubType);
    }

    @Bindable
    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
        notifyPropertyChanged(BR.observation);
    }

    @Bindable
    public List<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<String> characteristics) {
        this.characteristics = characteristics;
        notifyPropertyChanged(BR.characteristics);
    }

    @Bindable
    public long getTownHousePrice() {
        return townHousePrice;
    }

    public void setTownHousePrice(long townHousePrice) {
        this.townHousePrice = townHousePrice;
        notifyPropertyChanged(BR.townHousePrice);
    }

    @Bindable
    public String getPropertySubType() {
        return propertySubType;
    }

    public void setPropertySubType(String propertySubType) {
        this.propertySubType = propertySubType;
        notifyPropertyChanged(BR.propertySubType);
    }

    @Bindable
    public List<String> getCommonCharacteristics() {
        return commonCharacteristics;
    }

    public void setCommonCharacteristics(List<String> commonCharacteristics) {
        this.commonCharacteristics = commonCharacteristics;
        notifyPropertyChanged(BR.commonCharacteristics);
    }

    @Bindable
    public String getComplementInfo() {
        return complementInfo;
    }

    public void setComplementInfo(String complementInfo) {
        this.complementInfo = complementInfo;
        notifyPropertyChanged(BR.complementInfo);
    }
}
