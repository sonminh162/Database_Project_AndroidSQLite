package com.lifetime.csdl_pj4.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Playground implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "playground_name_field")
    private String playgroundName;

    @ColumnInfo(name = "place_name_field")
    private String placeName;

    @ColumnInfo(name = "playground_type_field")
    private String type;

    public Playground(String playgroundName, String placeName, String type) {
        this.playgroundName = playgroundName;
        this.placeName = placeName;
        this.type = type;
    }

    public String getPlaygroundName() {
        return playgroundName;
    }

    public void setPlaygroundName(String playgroundName) {
        this.playgroundName = playgroundName;
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
