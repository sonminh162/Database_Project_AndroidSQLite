package com.lifetime.csdl_pj4.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Place implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "place_name_field")
    private String placeName;

    @ColumnInfo(name = "city_name_field")
    private String cityName;

    @ColumnInfo(name = "type_field")
    private String type;

    public Place(String placeName, String cityName, String type) {
        this.placeName = placeName;
        this.cityName = cityName;
        this.type = type;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
