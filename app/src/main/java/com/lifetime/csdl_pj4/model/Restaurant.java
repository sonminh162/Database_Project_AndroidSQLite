package com.lifetime.csdl_pj4.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Restaurant implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "restaurant_name_field")
    private String restaurantName;

    @ColumnInfo(name = "place_name_field")
    private String placeName;

    @ColumnInfo(name = "restaurant_type_field")
    private String type;

    @ColumnInfo(name = "restaurant_phone_field")
    private String restaurantPhone;

    public Restaurant(String restaurantName, String placeName, String type, String restaurantPhone) {
        this.restaurantName = restaurantName;
        this.placeName = placeName;
        this.type = type;
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
