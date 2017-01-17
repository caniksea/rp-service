/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rp.caniksea.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author caniksea
 */
public class Country implements Serializable, Comparable<Country>{

    private String country_iso_code, country_name, country_currency, dialing_code, flag_path;
    private double exchange_rate, dynamic_fee, static_fee;

    private Country() {
    }

    public Country(Builder builder) {
        this.country_iso_code = builder.country_iso_code;
        this.country_name = builder.country_name;
        this.country_currency = builder.country_currency;
        this.dialing_code = builder.dialing_code;
        this.flag_path = builder.flag_path;
        this.exchange_rate = builder.exchange_rate;
        this.dynamic_fee = builder.dynamic_fee;
        this.static_fee = builder.static_fee;
    }

    public String getCountry_iso_code() {
        return country_iso_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getCountry_currency() {
        return country_currency;
    }

    public String getDialing_code() {
        return dialing_code;
    }

    public String getFlag_path() {
        return flag_path;
    }

    public double getExchange_rate() {
        return exchange_rate;
    }

    public double getDynamic_fee() {
        return dynamic_fee;
    }

    public double getStatic_fee() {
        return static_fee;
    }

    public static class Builder {

        private String country_iso_code, country_name, country_currency, dialing_code, flag_path;
        private double exchange_rate, dynamic_fee, static_fee;
        
        public Builder country_iso_code(String country_iso_code){
            this.country_iso_code = country_iso_code;
            return this;
        }
        
        public Builder country_name(String country_name){
            this.country_name = country_name;
            return this;
        }
        
        public Builder country_currency(String country_currency){
            this.country_currency = country_currency;
            return this;
        }
        
        public Builder dialing_code(String dialing_code){
            this.dialing_code = dialing_code;
            return this;
        }
        
        public Builder flag_path(String flag_path){
            this.flag_path = flag_path;
            return this;
        }
        
        public Builder exchange_rate(double exchange_rate){
            this.exchange_rate = exchange_rate;
            return this;
        }
        
        public Builder dynamic_fee(double dynamic_fee){
            this.dynamic_fee = dynamic_fee;
            return this;
        }
        
        public Builder static_fee(double static_fee){
            this.static_fee = static_fee;
            return this;
        }
        
        public Builder copy(Country country){
            this.country_iso_code = country.country_iso_code;
            this.country_name = country.country_name;
            this.country_currency = country.country_currency;
            this.dialing_code = country.dialing_code;
            this.flag_path = country.flag_path;
            this.exchange_rate = country.exchange_rate;
            this.dynamic_fee = country.dynamic_fee;
            this.static_fee = country.static_fee;
            
            return this;
        }
        
        public Country build(){
            return new Country(this);
        }
    }
    
    public static Builder builder(){
        return new Builder();
    }

    @Override
    public int compareTo(Country country) {
        return country_iso_code.compareTo(country.country_iso_code);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.country_iso_code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Country other = (Country) obj;
        return Objects.equals(this.country_iso_code, other.country_iso_code);
    }

    @Override
    public String toString() {
        return "Country{" + "country_iso_code=" + country_iso_code + ", country_name=" + country_name + ", country_currency=" + country_currency + ", dialing_code=" + dialing_code + ", flag_path=" + flag_path + ", exchange_rate=" + exchange_rate + ", dynamic_fee=" + dynamic_fee + ", static_fee=" + static_fee + '}';
    }

}
