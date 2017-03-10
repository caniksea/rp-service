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
public class User implements Serializable, Comparable<User> {

    private String user_id, email, password, first_name, last_name, phone, status,
            is_onlinecustomer, activation_key, fe_update_acc, dob, address, city,
            country, postal_code, fax, mobile;
    private int contact_id;

    private User() {
    }

    public User(Builder builder) {
        this.contact_id = builder.contact_id;
        this.user_id = builder.user_id;
        this.email = builder.email;
        this.password = builder.password;
        this.first_name = builder.first_name;
        this.last_name = builder.last_name;
        this.phone = builder.phone;
        this.status = builder.status;
        this.is_onlinecustomer = builder.is_onlinecustomer;
        this.activation_key = builder.activation_key;
        this.fe_update_acc = builder.fe_update_acc;
        this.dob = builder.dob;
        this.address = builder.address;
        this.city = builder.city;
        this.country = builder.country;
        this.postal_code = builder.postal_code;
        this.fax = builder.fax;
        this.mobile = builder.mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getFax() {
        return fax;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUser_id() {
        return user_id;
    }

    public int getContact_id() {
        return contact_id;
    }

    public String getStatus() {
        return status;
    }

    public String getIs_onlinecustomer() {
        return is_onlinecustomer;
    }

    public String getActivation_key() {
        return activation_key;
    }

    public String getFe_update_acc() {
        return fe_update_acc;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public static class Builder {

        private String user_id, email, password, first_name, last_name, phone, status,
                is_onlinecustomer, activation_key, fe_update_acc, dob, address, city,
                country, postal_code, fax, mobile;
        private int contact_id;

        public Builder contact_id(int contact_id) {
            this.contact_id = contact_id;
            return this;
        }

        public Builder user_id(String id) {
            this.user_id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder first_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        public Builder last_name(String last_name) {
            this.last_name = last_name;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder is_onlinecustomer(String is_onlinecustomer) {
            this.is_onlinecustomer = is_onlinecustomer;
            return this;
        }

        public Builder activation_key(String activation_key) {
            this.activation_key = activation_key;
            return this;
        }

        public Builder fe_update_acc(String fe_update_acc) {
            this.fe_update_acc = fe_update_acc;
            return this;
        }

        public Builder dob(String dob) {
            this.dob = dob;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder postal_code(String postal_code) {
            this.postal_code = postal_code;
            return this;
        }

        public Builder fax(String fax) {
            this.fax = fax;
            return this;
        }

        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder copy(User user) {
            this.contact_id = user.contact_id;
            this.user_id = user.user_id;
            this.email = user.email;
            this.password = user.password;
            this.first_name = user.first_name;
            this.last_name = user.last_name;
            this.phone = user.phone;
            this.status = user.status;
            this.is_onlinecustomer = user.is_onlinecustomer;
            this.activation_key = user.activation_key;
            this.fe_update_acc = user.fe_update_acc;
            this.dob = user.dob;
            this.address = user.address;
            this.city = user.city;
            this.country = user.country;
            this.postal_code = user.postal_code;
            this.fax = user.fax;
            this.mobile = user.mobile;

            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int compareTo(User t) {
        return user_id.compareTo(t.user_id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.user_id);
        hash = 23 * hash + this.contact_id;
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
        final User other = (User) obj;
        if (this.contact_id != other.contact_id) {
            return false;
        }
        return Objects.equals(this.user_id, other.user_id);
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", email=" + email + ", password=" + password + ", first_name=" + first_name + ", last_name=" + last_name + ", phone=" + phone + ", status=" + status + ", is_onlinecustomer=" + is_onlinecustomer + ", activation_key=" + activation_key + ", fe_update_acc=" + fe_update_acc + ", dob=" + dob + ", address=" + address + ", city=" + city + ", country=" + country + ", postal_code=" + postal_code + ", fax=" + fax + ", mobile=" + mobile + ", contact_id=" + contact_id + '}';
    }

}
