package com.lifetime.csdl_pj4.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Hotel implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "hotel_name_field")
    private String hotelName;

    @ColumnInfo(name = "place_name_field")
    private String placeName;

    @ColumnInfo(name = "hotel_phone_field")
    private String hotelPhone;

    public Hotel(String hotelName, String placeName, String hotelPhone) {
        this.hotelName = hotelName;
        this.placeName = placeName;
        this.hotelPhone = hotelPhone;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }
}
