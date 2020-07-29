package com.ztt.kafka.stream.m30.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PhotoCamera implements Serializable {

    String name;
    BigDecimal latitude;
    BigDecimal longitude;
    String url;
    Date date;
    String provincia;

    public PhotoCamera(){}

    public PhotoCamera(String name, BigDecimal latitude, BigDecimal longitude, String url, Date date, String provincia) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = url;
        this.date = date;
        this.provincia = provincia;
    }

    public PhotoCamera(String name, BigDecimal latitude, BigDecimal longitude, String url, Date date) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = url;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
