/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rp.caniksea.ws;

import com.google.gson.Gson;
import com.rp.caniksea.controller.RPEngine;
import javax.jws.WebService;
import org.apache.log4j.Logger;

/**
 *
 * @author caniksea
 */
@WebService(portName = "RPServicePort", serviceName = "RPServiceImpl", 
        targetNamespace = "http://ws.caniksea.rp.com", endpointInterface = "com.rp.caniksea.ws.RPService")
public class RPServiceImpl implements RPService{
    
    private static final Logger LOG = Logger.getLogger(RPServiceImpl.class);
    private final Gson GSON = new Gson();
    private final RPEngine RP_ENGINE = new RPEngine();

    @Override
    public String login(String request) {
        return GSON.toJson(RP_ENGINE.login(request));
    }

    @Override
    public String getCountries() {
        return GSON.toJson(RP_ENGINE.getCountries());
    }

    @Override
    public String getCountry(String request) {
        return GSON.toJson(RP_ENGINE.getCountry(request));
    }

    @Override
    public String getPaymethods() {
        return GSON.toJson(RP_ENGINE.getPaymentMethods());
    }

    @Override
    public String registerCustomer(String request) {
        return GSON.toJson(RP_ENGINE.register(request));
    }

    @Override
    public String initiateTransaction(String request) {
        return GSON.toJson(RP_ENGINE.initiateTransaction(request));
    }

    @Override
    public String getBeneficiaries(String request) {
        return GSON.toJson(RP_ENGINE.getBeneficiaries(request));
    }

    @Override
    public String getBanksInCountry(String request) {
        return GSON.toJson(RP_ENGINE.getBanksInCountry(request));
    }

    @Override
    public String saveBeneficiary(String request) {
        return GSON.toJson(RP_ENGINE.saveBeneficiary(request));
    }

    @Override
    public String getBeneficiary(String request) {
        return GSON.toJson(RP_ENGINE.getBeneficiary(request));
    }

    @Override
    public String getBank(String request) {
        return GSON.toJson(RP_ENGINE.getBank(request));
    }

    @Override
    public String saveTransaction(String request) {
        return GSON.toJson(RP_ENGINE.saveTransaction(request));
    }

    @Override
    public String getPendingTransactions(String request) {
        return GSON.toJson(RP_ENGINE.getPendingTransactions(request));
    }

    @Override
    public String getAllTransactions(String request) {
        return GSON.toJson(RP_ENGINE.getAllTransaction(request));
    }

    @Override
    public String getSuccessfulTransactions(String request) {
        return GSON.toJson(RP_ENGINE.getSuccessfulTransactions(request));
    }

    @Override
    public String getFailedTransactions(String request) {
        return GSON.toJson(RP_ENGINE.getFailedTransactions(request));
    }

    @Override
    public String getBanks() {
        return GSON.toJson(RP_ENGINE.getBanks());
    }

    @Override
    public String deleteBeneficiary(String request) {
        return GSON.toJson(RP_ENGINE.deleteBeneficiary(request));
    }

    @Override
    public String UpdateUser(String request) {
        return GSON.toJson(RP_ENGINE.updateUser(request));
    }
    
}
