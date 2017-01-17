/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rp.caniksea.model;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author caniksea
 */
public class GenericCollectionResponse implements Serializable {

    private String response_code, response_description;
    private Set<Object> response_data;

    private GenericCollectionResponse() {
    }

    public GenericCollectionResponse(Builder builder) {
        this.response_code = builder.response_code;
        this.response_description = builder.response_description;
        this.response_data = builder.response_data;
    }

    public String getResponse_code() {
        return response_code;
    }

    public String getResponse_description() {
        return response_description;
    }

    public Set<Object> getResponse_data() {
        return response_data;
    }

    public static class Builder {

        private String response_code, response_description;
        private Set<Object> response_data;

        public Builder response_code(String response_code) {
            this.response_code = response_code;
            return this;
        }

        public Builder response_description(String response_description) {
            this.response_description = response_description;
            return this;
        }

        public Builder response_data(Set<Object> response_data) {
            this.response_data = response_data;
            return this;
        }

        public Builder copy(GenericCollectionResponse gcr) {
            this.response_code = gcr.response_code;
            this.response_description = gcr.response_description;
            this.response_data = gcr.response_data;
            return this;
        }

        public GenericCollectionResponse build() {
            return new GenericCollectionResponse(this);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "GenericCollectionResponse{" + "response_code=" + response_code + ", response_description=" + response_description + ", response_data=" + response_data + '}';
    }

}
