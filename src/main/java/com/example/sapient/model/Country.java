package com.example.sapient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    @JsonProperty("country_id")
    private String country_id;
    @JsonProperty("country_name")
    private String country_name;

}
