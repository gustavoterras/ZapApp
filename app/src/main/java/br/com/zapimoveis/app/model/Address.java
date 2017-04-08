package br.com.zapimoveis.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

import io.realm.RealmObject;

/**
 * Created by gustavoterras on 06/04/17.
 */

public class Address extends RealmObject {

    @SerializedName("Logradouro")
    private String street = "";

    @SerializedName("Numero")
    private String number = "";

    @SerializedName("Complemento")
    private String complement = "";

    @SerializedName("CEP")
    private String zipCode = "";

    @SerializedName("Bairro")
    private String neighborhood = "";

    @SerializedName("Cidade")
    private String city = "";

    @SerializedName("Estado")
    private String state = "";

    @SerializedName("Zona")
    private String zone = "";

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String toString(){
        return String.format(Locale.getDefault(), "%s %s, %s ", street, number, neighborhood).toLowerCase();
    }

    public String toGeo(){
        return String.format(Locale.getDefault(), "%s %s, %s ", street, city, state).toLowerCase();
    }
}
