/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rp.caniksea.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author caniksea
 */
public class Sale implements Serializable {

    private String originator_id, beneficiary_id, order_id, currency, bank_name, beneficiary_account_no, beneficiary_country, status;
    private double receiving_amount, exchange_rate, sending_amount, fee, total;
    private int sale_id;
    private Date order_date, process_date;

    private Sale() {
    }

    public Sale(Builder builder) {
        this.originator_id = builder.originator_id;
        this.beneficiary_id = builder.beneficiary_id;
        this.order_id = builder.order_id;
        this.currency = builder.currency;
        this.bank_name = builder.bank_name;
        this.beneficiary_account_no = builder.beneficiary_account_no;
        this.beneficiary_country = builder.beneficiary_country;
        this.receiving_amount = builder.receiving_amount;
        this.status = builder.status;
        this.exchange_rate = builder.exchange_rate;
        this.sending_amount = builder.sending_amount;
        this.fee = builder.fee;
        this.total = builder.total;
        this.sale_id = builder.sale_id;
        this.order_date = builder.order_date;
        this.process_date = builder.process_date;
    }

    public String getStatus() {
        return status;
    }

    public double getFee() {
        return fee;
    }

    public String getOriginator_id() {
        return originator_id;
    }

    public String getBeneficiary_id() {
        return beneficiary_id;
    }

    public String getBeneficiary_country() {
        return beneficiary_country;
    }

    public double getTotal() {
        return total;
    }

    public int getSale_id() {
        return sale_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public Date getProcess_date() {
        return process_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getCurrency() {
        return currency;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getBeneficiary_account_no() {
        return beneficiary_account_no;
    }

    public double getReceiving_amount() {
        return receiving_amount;
    }

    public double getExchange_rate() {
        return exchange_rate;
    }

    public double getSending_amount() {
        return sending_amount;
    }

    public static class Builder {

        private String originator_id, beneficiary_id, order_id, currency, bank_name, beneficiary_account_no, beneficiary_country, status;
        private double receiving_amount, exchange_rate, sending_amount, fee, total;
        private int sale_id;
        private Date order_date, process_date;

        public Builder originator_id(String originator_id) {
            this.originator_id = originator_id;
            return this;
        }

        public Builder beneficiary_country(String beneficiary_country) {
            this.beneficiary_country = beneficiary_country;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder beneficiary_id(String beneficiary_id) {
            this.beneficiary_id = beneficiary_id;
            return this;
        }

        public Builder order_id(String order_id) {
            this.order_id = order_id;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder bank_name(String bank_name) {
            this.bank_name = bank_name;
            return this;
        }

        public Builder beneficiary_account_no(String beneficiary_account_no) {
            this.beneficiary_account_no = beneficiary_account_no;
            return this;
        }

        public Builder receiving_amount(double receiving_amount) {
            this.receiving_amount = receiving_amount;
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

        public Builder exchange_rate(double exchange_rate) {
            this.exchange_rate = exchange_rate;
            return this;
        }

        public Builder sending_amount(double sending_amount) {
            this.sending_amount = sending_amount;
            return this;
        }

        public Builder sale_id(int sale_id) {
            this.sale_id = sale_id;
            return this;
        }

        public Builder order_date(Date order_date) {
            this.order_date = order_date;
            return this;
        }

        public Builder process_date(Date process_date) {
            this.process_date = process_date;
            return this;
        }

        public Builder copy(Sale sale) {
            this.originator_id = sale.originator_id;
            this.beneficiary_id = sale.beneficiary_id;
            this.order_id = sale.order_id;
            this.currency = sale.currency;
            this.bank_name = sale.bank_name;
            this.beneficiary_account_no = sale.beneficiary_account_no;
            this.beneficiary_country = sale.beneficiary_country;
            this.status = sale.status;
            this.receiving_amount = sale.receiving_amount;
            this.exchange_rate = sale.exchange_rate;
            this.sending_amount = sale.sending_amount;
            this.fee = sale.fee;
            this.total = sale.total;
            this.sale_id = sale.sale_id;
            this.order_date = sale.order_date;
            this.process_date = sale.process_date;

            return this;
        }

        public Sale build() {
            return new Sale(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.originator_id);
        hash = 97 * hash + Objects.hashCode(this.beneficiary_id);
        hash = 97 * hash + Objects.hashCode(this.order_id);
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
        final Sale other = (Sale) obj;
        if (!Objects.equals(this.originator_id, other.originator_id)) {
            return false;
        }
        if (!Objects.equals(this.beneficiary_id, other.beneficiary_id)) {
            return false;
        }
        return Objects.equals(this.order_id, other.order_id);
    }

    @Override
    public String toString() {
        return "Sale{" + 
                "originator_id=" + originator_id + 
                ", beneficiary_id=" + beneficiary_id + 
                ", order_id=" + order_id + 
                ", currency=" + currency + 
                ", bank_name=" + bank_name + 
                ", beneficiary_account_no=" + beneficiary_account_no + 
                ", beneficiary_country=" + beneficiary_country + 
                ", status=" + status +
                ", receiving_amount=" + receiving_amount + 
                ", exchange_rate=" + exchange_rate + 
                ", sending_amount=" + sending_amount + 
                ", fee=" + fee + 
                ", total=" + total + 
                ", sale_id=" + sale_id + 
                ", order_date=" + order_date + 
                ", process_date=" + process_date + '}';
    }

}
