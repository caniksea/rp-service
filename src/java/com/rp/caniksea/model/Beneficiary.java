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
public class Beneficiary implements Serializable, Comparable<Beneficiary>{

    private int beneficiary_id, bank_id, sender_id;
    private String bank_branch, account_number, first_name, middle_name, last_name, country_code;

    private Beneficiary() {
    }

    public Beneficiary(Builder builder) {
        this.beneficiary_id = builder.beneficiary_id;
        this.bank_id = builder.bank_id;
        this.bank_branch = builder.bank_branch;
        this.account_number = builder.account_number;
        this.first_name = builder.first_name;
        this.middle_name = builder.middle_name;
        this.last_name = builder.last_name;
        this.sender_id = builder.sender_id;
        this.country_code = builder.country_code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public int getSender_id() {
        return sender_id;
    }

    public int getBeneficiary_id() {
        return beneficiary_id;
    }

    public int getBank_id() {
        return bank_id;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public String getAccount_number() {
        return account_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public static class Builder {

        private int beneficiary_id, bank_id, sender_id;
        private String bank_branch, account_number, first_name, middle_name, last_name, country_code;

        public Builder beneficiary_id(int beneficiary_id) {
            this.beneficiary_id = beneficiary_id;
            return this;
        }

        public Builder bank_id(int bank_id) {
            this.bank_id = bank_id;
            return this;
        }

        public Builder bank_branch(String bank_branch) {
            this.bank_branch = bank_branch;
            return this;
        }

        public Builder account_number(String account_number) {
            this.account_number = account_number;
            return this;
        }

        public Builder first_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        public Builder middle_name(String middle_name) {
            this.middle_name = middle_name;
            return this;
        }

        public Builder last_name(String last_name) {
            this.last_name = last_name;
            return this;
        }
        
        public Builder sender_id(int sender_id){
            this.sender_id = sender_id;
            return this;
        }
        
        public Builder country_code(String country_code){
            this.country_code = country_code;
            return this;
        }

        public Builder copy(Beneficiary beneficiary) {
            this.beneficiary_id = beneficiary.beneficiary_id;
            this.bank_id = beneficiary.bank_id;
            this.bank_branch = beneficiary.bank_branch;
            this.account_number = beneficiary.account_number;
            this.first_name = beneficiary.first_name;
            this.middle_name = beneficiary.middle_name;
            this.last_name = beneficiary.last_name;
            this.sender_id = beneficiary.sender_id;
            this.country_code = beneficiary.country_code;

            return this;
        }
        
        public Beneficiary build(){
            return new Beneficiary(this);
        }

    }
    
    public static Builder builder(){
        return new Builder();
    }

    @Override
    public int compareTo(Beneficiary t) {
        return beneficiary_id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.beneficiary_id;
        hash = 53 * hash + Objects.hashCode(this.sender_id);
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
        final Beneficiary other = (Beneficiary) obj;
        if (this.beneficiary_id != other.beneficiary_id) {
            return false;
        }
        return Objects.equals(this.sender_id, other.sender_id);
    }

    @Override
    public String toString() {
        return "Beneficiary{" + "beneficiary_id=" + beneficiary_id + ", bank_id=" + bank_id 
                + ", bank_branch=" + bank_branch + ", account_number=" + account_number 
                + ", first_name=" + first_name + ", middle_name=" + middle_name + ", last_name=" 
                + last_name + ", sender_id=" + sender_id + ", country_code=" + country_code + '}';
    }

}
