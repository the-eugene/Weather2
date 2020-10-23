
package com.example.weather2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Skies {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("main")
    @Expose
    public String mainCondition;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icon")
    @Expose
    public String icon;

}
