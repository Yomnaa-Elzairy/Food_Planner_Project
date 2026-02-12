package com.example.foodplannerproject.data.search.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("idIngredient")
    private String idIngredient;

    @SerializedName("strIngredient")
    private String strIngredient;

    @SerializedName("strDescription")
    private String strDescription;

    @SerializedName("strType")
    private String strType;
    @SerializedName("strThumb")
    private String image;


    public String getIdIngredient() {
        return idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public String getStrType() {
        return strType;
    }

    public String getImage() {
        return image;
    }
}
