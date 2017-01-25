/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rp.caniksea.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author caniksea
 */
@WebService(targetNamespace = "http://ws.caniksea.rp.com", name = "RPService")
public interface RPService {
    
    @WebMethod(operationName = "login")
    public String login(@WebParam(name = "reuest") String request);
    @WebMethod(operationName = "getCountries")
    public String getCountries();
    @WebMethod(operationName = "getCountry")
    public String getCountry(@WebParam(name = "request") String request);
    @WebMethod(operationName = "getPaymethods")
    public String getPaymethods();
    @WebMethod(operationName = "register")
    public String registerCustomer(@WebParam(name = "request") String request);
    @WebMethod(operationName = "initiateTransaction")
    public String initiateTransaction(@WebParam(name = "request") String request);
    @WebMethod(operationName = "getBeneficiaries")
    public String getBeneficiaries(@WebParam(name = "request") String request);
    @WebMethod(operationName = "getBanksInCountry")
    public String getBanksInCountry(@WebParam(name = "request") String request);
    @WebMethod(operationName = "saveBeneficiary")
    public String saveBeneficiary(@WebParam(name = "request") String request);
    @WebMethod(operationName = "getBeneficiary")
    public String getBeneficiary(@WebParam(name = "request") String request);
    @WebMethod(operationName = "getBank")
    public String getBank(@WebParam(name = "request") String request);
    @WebMethod(operationName = "saveTransaction")
    public String saveTransaction(@WebParam(name = "request") String request);
    @WebMethod(operationName = "getPendingTransaction")
    public String getPendingTransactions(@WebParam(name = "request") String request);
    @WebMethod(operationName = "getAllTransaction")
    public String getAllTransaction(@WebParam(name = "request") String request);
    
}
