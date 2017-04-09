package br.com.zapimoveis.app.model;

import android.text.Html;
import android.text.Spanned;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.util.Locale;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class Property extends RealmObject{

    @SerializedName("CodImovel")
    @PrimaryKey
    private long codProperty;

    @SerializedName("TipoImovel")
    private String propertyType;

    @SerializedName("Endereco")
    private Address address;

    @SerializedName("PrecoVenda")
    private long salesPrice;

    @SerializedName("Dormitorios")
    private int bedrooms;

    @SerializedName("Suites")
    private int suites;

    @SerializedName("Vagas")
    private int parking;

    @SerializedName("AreaUtil")
    private int usefulArea;

    @SerializedName("AreaTotal")
    private int totalArea;

    @SerializedName("Cliente")
    private Client client;

    @SerializedName("UrlImagem")
    private String urlImage;

    @SerializedName("StatusQualidadeTotal")
    private String constructionStage;

    @SerializedName("SubTipoOferta")
    private String offerSubType;

    @SerializedName("SubtipoImovel")
    private String propertySubType;

    public long getCodProperty() {
        return codProperty;
    }

    public void setCodProperty(long codProperty) {
        this.codProperty = codProperty;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(long salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getSuites() {
        return suites;
    }

    public void setSuites(int suites) {
        this.suites = suites;
    }

    public int getParking() {
        return parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public int getUsefulArea() {
        return usefulArea;
    }

    public void setUsefulArea(int usefulArea) {
        this.usefulArea = usefulArea;
    }

    public int getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(int totalArea) {
        this.totalArea = totalArea;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getConstructionStage() {
        return constructionStage;
    }

    public void setConstructionStage(String constructionStage) {
        this.constructionStage = constructionStage;
    }

    public String getOfferSubType() {
        return offerSubType;
    }

    public void setOfferSubType(String offerSubType) {
        this.offerSubType = offerSubType;
    }

    public String getPropertySubType() {
        return propertySubType;
    }

    public void setPropertySubType(String propertySubType) {
        this.propertySubType = propertySubType;
    }

    public Spanned formattedValue(){
        String value = DecimalFormat.getCurrencyInstance(Locale.getDefault()).format(salesPrice / 100);
        return Html.fromHtml("<small>Valor</small><big>" + value + "<big>");
    }

    public Spanned formattedDescription(){
        String value = DecimalFormat.getCurrencyInstance(Locale.getDefault()).format(salesPrice / 100);
        return Html.fromHtml(propertyType + "<br><small>Á Venda</small><br><big><big><b>" + value + "<b><big></big>");
    }

    public String formattedArea(){
        return totalArea + "m2";
    }
}
