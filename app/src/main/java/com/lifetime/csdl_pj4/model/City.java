package com.lifetime.csdl_pj4.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class City implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "city_name_field")
    private String cityName;

    public City(String cityName) {
        this.cityName = cityName;
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    public void setCityName(@NonNull String cityName) {
        this.cityName = cityName;
    }
}
