package com.example.b3tempoappzaher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class TempoDate {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("couleur")
    @Expose
    private String couleur;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

}