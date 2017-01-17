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
public class PaymentMethod implements Serializable, Comparable<PaymentMethod>{

    private int id;
    private String method_name;

    private PaymentMethod() {
    }

    public PaymentMethod(Builder builder) {
        this.id = builder.id;
        this.method_name = builder.method_name;
    }

    public int getId() {
        return id;
    }

    public String getMethod_name() {
        return method_name;
    }

    public static class Builder {
        private int id;
        private String method_name;
        
        public Builder id(int id){
            this.id = id;
            return this;
        }
        
        public Builder method_name(String method_name){
            this.method_name = method_name;
            return this;
        }
        
        public Builder copy(PaymentMethod paymentMethod){
            this.id = paymentMethod.id;
            this.method_name = paymentMethod.method_name;
            return this;
        }
        
        public PaymentMethod build(){
            return new PaymentMethod(this);
        }
    }
    
    public static Builder builder(){
        return new Builder();
    }

    @Override
    public int compareTo(PaymentMethod paymentMethod) {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
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
        final PaymentMethod other = (PaymentMethod) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" + "id=" + id + ", method_name=" + method_name + '}';
    }

}
