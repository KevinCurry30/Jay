package com.diligroup.bean;

import com.baidu.location.Address;

import java.io.Serializable;

/**
 * Created by Kevin on 2016/6/30.
 */
public class UserLocationBean implements Serializable {
    public double Longitude;
    public double Latitud;
    public String city;
    public String provice;
    public Address address;
    public String time;

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
