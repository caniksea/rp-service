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
public class TransactionRequest implements Serializable, Comparable<TransactionRequest> {

    private String transaction_id, merchant_code, destination_country, pay_method, response_code, response_description;
    private double source_amount, destination_amount, fee, total;
    private User customer;

    private TransactionRequest() {
    }

    public TransactionRequest(Builder builder) {
        this.transaction_id = builder.transaction_id;
        this.merchant_code = builder.merchant_code;
        this.destination_country = builder.destination_country;
        this.pay_method = builder.pay_method;
        this.response_code = builder.response_code;
        this.response_description = builder.response_description;
        this.source_amount = builder.source_amount;
        this.destination_amount = builder.destination_amount;
        this.fee = builder.fee;
        this.total = builder.total;
        this.customer = builder.customer;
    }

    public String getResponse_code() {
        return response_code;
    }

    public String getResponse_description() {
        return response_description;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public User getCustomer() {
        return customer;
    }

    public String getMerchant_code() {
        return merchant_code;
    }

    public String getDestination_country() {
        return destination_country;
    }

    public String getPay_method() {
        return pay_method;
    }

    public double getSource_amount() {
        return source_amount;
    }

    public double getDestination_amount() {
        return destination_amount;
    }

    public double getFee() {
        return fee;
    }

    public double getTotal() {
        return total;
    }

    public static class Builder {

        private String transaction_id, merchant_code, destination_country, pay_method, response_code, response_description;
        private double source_amount, destination_amount, fee, total;
        private User customer;

        public Builder transaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
            return this;
        }

        public Builder merchant_code(String merchant_code) {
            this.merchant_code = merchant_code;
            return this;
        }

        public Builder pay_method(String pay_method) {
            this.pay_method = pay_method;
            return this;
        }

        public Builder response_code(String response_code) {
            this.response_code = response_code;
            return this;
        }

        public Builder response_description(String response_description) {
            this.response_description = response_description;
            return this;
        }

        public Builder destination_country(String destination_country) {
            this.destination_country = destination_country;
            return this;
        }

        public Builder source_amount(double source_amount) {
            this.source_amount = source_amount;
            return this;
        }

        public Builder destination_amount(double destination_amount) {
            this.destination_amount = destination_amount;
            return this;
        }

        public Builder fee(double fee) {
            this.fee = fee;
            return this;
        }

        public Builder total(double total) {
            this.total = total;
            return this;
        }

        public Builder customer(User customer) {
            this.customer = customer;
            return this;
        }

        public Builder copy(TransactionRequest transactionRequest) {
            this.transaction_id = transactionRequest.transaction_id;
            this.merchant_code = transactionRequest.merchant_code;
            this.destination_country = transactionRequest.destination_country;
            this.pay_method = transactionRequest.pay_method;
            this.response_code = transactionRequest.response_code;
            this.response_description = transactionRequest.response_description;
            this.source_amount = transactionRequest.source_amount;
            this.destination_amount = transactionRequest.destination_amount;
            this.fee = transactionRequest.fee;
            this.total = transactionRequest.total;
            this.customer = transactionRequest.customer;

            return this;

        }

        public TransactionRequest build() {
            return new TransactionRequest(this);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int compareTo(TransactionRequest t) {
        return transaction_id.compareTo(t.transaction_id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.transaction_id);
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
        final TransactionRequest other = (TransactionRequest) obj;
        return Objects.equals(this.transaction_id, other.transaction_id);
    }

}
