package com.example.macbookpro.retrofittesting.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class RESPONSE_OBJECT  {

    @SerializedName("RES_DATA")
    private JsonObject RES_DATA;


    public JsonObject getRESP_DATA() {
        return RES_DATA;
    }

    public void setRESP_DATA(JsonObject RESP_DATA) {
        this.RES_DATA = RES_DATA;
    }

    public String toString(){
        return this.getRESP_DATA()+"";
    }
}
