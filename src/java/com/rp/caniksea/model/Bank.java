/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rp.caniksea.model;

import java.io.Serializable;

/**
 *
 * @author caniksea
 */
public class Bank implements Serializable, Comparable<Bank> {

    private int bank_id;
    private String bank_name, bank_code, bank_alias, country_code;

    private Bank() {
    }

    public Bank(Builder builder) {
        this.bank_id = builder.bank_id;
        this.bank_name = builder.bank_name;
        this.bank_code = builder.bank_code;
        this.bank_alias = builder.bank_alias;
        this.country_code = builder.country_code;
    }

    public int getBank_id() {
        return bank_id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getBank_code() {
        return bank_code;
    }

    public String getBank_alias() {
        return bank_alias;
    }

    public String getCountry_code() {
        return country_code;
    }

    public static class Builder {

        private int bank_id;
        private String bank_name, bank_code, bank_alias, country_code;

        public Builder bank_id(int bank_id) {
            this.bank_id = bank_id;
            return this;
        }

        public Builder bank_name(String bank_name) {
            this.bank_name = bank_name;
            return this;
        }

        public Builder bank_code(String bank_code) {
            this.bank_code = bank_code;
            return this;
        }

        public Builder bank_alias(String bank_alias) {
            this.bank_alias = bank_alias;
            return this;
        }

        public Builder country_code(String country_code) {
            this.country_code = country_code;
            return this;
        }

        public Builder copy(Bank bank) {
            this.bank_id = bank.bank_id;
            this.bank_name = bank.bank_name;
            this.bank_code = bank.bank_code;
            this.bank_alias = bank.bank_alias;
            this.country_code = bank.country_code;

            return this;
        }

        public Bank build() {
            return new Bank(this);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.bank_id;
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
        final Bank other = (Bank) obj;
        return this.bank_id == other.bank_id;
    }

    @Override
    public int compareTo(Bank t) {
        return bank_id;
    }

    @Override
    public String toString() {
        return "Bank{" + "bank_id=" + bank_id + ", bank_name=" + bank_name + ", bank_code=" + bank_code + ", bank_alias=" + bank_alias + ", country_code=" + country_code + '}';
    }

}
