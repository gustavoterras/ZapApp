package br.com.zapimoveis.app.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class PropertyDetail extends RealmObject {

    @SerializedName("CodImovel")
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

    @SerializedName("AreaUtil")
    private int totalArea;

    @SerializedName("Cliente")
    private Client client;

    @SerializedName("Fotos")
    private RealmList<RealmString> pictures;

    @SerializedName("SubTipoOferta")
    private String offerSubType;

    @SerializedName("Observacao")
    private String observation;

    @SerializedName("Caracteristicas")
    private RealmList<RealmString> characteristics;

    @SerializedName("PrecoCondominio")
    private long townHousePrice;

    @SerializedName("SubtipoImovel")
    private String propertySubType;

    @SerializedName("CaracteristicasComum")
    private RealmList<RealmString> commonCharacteristics;

    @SerializedName("InformacoesComplementares")
    private String complementationInfo;

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

    public RealmList<RealmString> getPictures() {
        return pictures;
    }

    public void setPictures(RealmList<RealmString> pictures) {
        this.pictures = pictures;
    }

    public String getOfferSubType() {
        return offerSubType;
    }

    public void setOfferSubType(String offerSubType) {
        this.offerSubType = offerSubType;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public RealmList<RealmString> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(RealmList<RealmString> characteristics) {
        this.characteristics = characteristics;
    }

    public long getTownHousePrice() {
        return townHousePrice;
    }

    public void setTownHousePrice(long townHousePrice) {
        this.townHousePrice = townHousePrice;
    }

    public String getPropertySubType() {
        return propertySubType;
    }

    public void setPropertySubType(String propertySubType) {
        this.propertySubType = propertySubType;
    }

    public RealmList<RealmString> getCommonCharacteristics() {
        return commonCharacteristics;
    }

    public void setCommonCharacteristics(RealmList<RealmString> commonCharacteristics) {
        this.commonCharacteristics = commonCharacteristics;
    }

    public String getComplementationInfo() {
        return complementationInfo;
    }

    public void setComplementationInfo(String complementationInfo) {
        this.complementationInfo = complementationInfo;
    }
}
