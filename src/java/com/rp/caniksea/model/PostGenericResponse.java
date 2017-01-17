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
public class PostGenericResponse implements Serializable {

    private String response_code, response_description;
    private Object data;

    private PostGenericResponse() {
    }

    public PostGenericResponse(Builder builder) {
        this.response_code = builder.response_code;
        this.response_description = builder.response_description;
        this.data = builder.data;
    }

    public String getResponse_code() {
        return response_code;
    }

    public String getResponse_description() {
        return response_description;
    }

    public Object getData() {
        return data;
    }

    public static class Builder {

        private String response_code, response_description;
        private Object data;
        
        public Builder response_code(String response_code){
            this.response_code = response_code;
            return this;
        }
        
        public Builder response_description(String response_description){
            this.response_description = response_description;
            return this;
        }
        
        public Builder data(Object data){
            this.data = data;
            return this;
        }
        
        public Builder copy(PostGenericResponse pgr){
            this.response_code = pgr.response_code;
            this.response_description = pgr.response_description;
            this.data = pgr.data;
            return this;
        }
        
        public PostGenericResponse build(){
            return new PostGenericResponse(this);
        }
    }
    
    public static Builder builder(){
        return new Builder();
    }

    @Override
    public String toString() {
        return "PostGenericResponse{" + "response_code=" + response_code + ", response_description=" + response_description + ", data=" + data + '}';
    }

}
