/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rp.caniksea.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rp.caniksea.db.DAO;
import com.rp.caniksea.model.Bank;
import com.rp.caniksea.model.Beneficiary;
import com.rp.caniksea.model.Country;
import com.rp.caniksea.model.GenericCollectionResponse;
import com.rp.caniksea.model.PaymentMethod;
import com.rp.caniksea.model.PostGenericResponse;
import com.rp.caniksea.model.Sale;
import com.rp.caniksea.model.TransactionRequest;
import com.rp.caniksea.model.User;
import com.rp.caniksea.util.Utility;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author caniksea
 */
public class RPEngine {

    private final DAO DAO = new DAO();
    private final DecimalFormat DF = new DecimalFormat("#0.00");
    private static final Logger LOG = Logger.getLogger(RPEngine.class);

    public Set<Country> getCountries() {
        return DAO.getCountries();
    }

    public Set<PaymentMethod> getPaymentMethods() {
        return DAO.getPaymentMethods();
    }

    public Set<Country> getCountry(String id) {
        return DAO.getCountry(id);
    }

    public PostGenericResponse register(String request) {
        JsonObject object = (JsonObject) new JsonParser().parse(request);
        User user = null;
        String response_description = "Success", response_code = "00";
        String firstName = (object.get("customer_firstname") == null || object.get("customer_firstname").getAsString() == null) ? ""
                : object.get("customer_firstname").getAsString();
        String lastName = (object.get("customer_lastname") == null || object.get("customer_lastname").getAsString() == null) ? ""
                : object.get("customer_lastname").getAsString();
        String email = (object.get("customer_email") == null || object.get("customer_email").getAsString() == null) ? ""
                : object.get("customer_email").getAsString();
        String password = (object.get("customer_password") == null || object.get("customer_password").getAsString() == null) ? ""
                : object.get("customer_password").getAsString();
        String userId = "";

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            response_code = "01";
            response_description = "Missing Parameter!";
        } else {
            try {
                password = DigestUtils.md5Hex(password); //new RPSecurity().hashPassword(password, userID);
                userId = Utility.generateUserID();
                user = User.builder().email(email).first_name(firstName)
                        .user_id(userId).last_name(lastName).password(password).status("Active").is_onlinecustomer("Y")
                        .activation_key("A").fe_update_acc("new").build();
                user = DAO.createUser(user);
                if (user == null) {
                    response_code = "04";
                    response_description = "User not created.";
                }
            } catch (Exception ex) {
                LOG.error("register - Exception: " + ex.getMessage());
                response_code = "04";
                response_description = "User not created.";
            }

        }
        return PostGenericResponse.builder().response_code(response_code).response_description(response_description).data(user).build();
    }

    public TransactionRequest initiateTransaction(String request) {
        JsonObject object = (JsonObject) new JsonParser().parse(request);
        String response_description = "Success", response_code = "00";
        String destination_country = (object.get("destination_country") == null || object.get("destination_country").getAsString() == null) ? ""
                : object.get("destination_country").getAsString();
        String pay_method = (object.get("pay_method") == null || object.get("pay_method").getAsString() == null) ? ""
                : object.get("pay_method").getAsString();
        String source_amount = (object.get("source_amount") == null || object.get("source_amount").getAsString() == null) ? ""
                : object.get("source_amount").getAsString();
        String merchant_code = (object.get("merchant_code") == null || object.get("merchant_code").getAsString() == null) ? ""
                : object.get("merchant_code").getAsString();
        double destination_amount = 0, fee = 0, total = 0, sourceAmount = 0;
        String rp_transaction_id = "";

        if (source_amount.isEmpty() || pay_method.isEmpty() || destination_country.isEmpty()) {
            response_code = "01";
            response_description = "Missing Parameter!";
        } else {
            try {
                sourceAmount = Double.parseDouble(source_amount);
                int payMethod = Integer.parseInt(pay_method);
                Set<PaymentMethod> paymentMethods = getPaymethod(payMethod);
                if (!paymentMethods.isEmpty()) {
                    Set<Country> countries = getCountry(destination_country);
                    if (!countries.isEmpty()) {
                        Country country = countries.iterator().next();
                        merchant_code = merchant_code.isEmpty() ? Utility.RP_MERCHANTCODE : merchant_code;
                        fee = country.getStatic_fee() + (country.getDynamic_fee() / 100) * sourceAmount;
                        destination_amount = sourceAmount * country.getExchange_rate();
                        fee = Double.parseDouble(DF.format(fee));
                        total = fee + sourceAmount;
                        total = Double.parseDouble(DF.format(total));
                        rp_transaction_id = Utility.generateRPTransactionID();
                    } else {
                        response_description = "Error: Could not get details for country [" + destination_country + "]";
                        response_code = "99";
                    }
                } else {
                    response_description = "Error: Payment Method [" + pay_method + "] not supported.";
                    response_code = "99";
                }
            } catch (NumberFormatException e) {
                response_description = "User Error: Incorrect source amount [" + source_amount + "] or pay method [" + pay_method + "]";
                response_code = "99";
            }
        }

        return TransactionRequest.builder().destination_amount(destination_amount)
                .destination_country(destination_country).fee(fee).merchant_code(merchant_code).pay_method(pay_method)
                .source_amount(sourceAmount).total(total).transaction_id(rp_transaction_id).response_code(response_code).response_description(response_description).build();

    }

    public Set<PaymentMethod> getPaymethod(int method) {
        return DAO.getPaymethod(method);
    }

    public GenericCollectionResponse getBeneficiaries(String request) {
        JsonObject object = (JsonObject) new JsonParser().parse(request);
        String user_id = (object.get("user_id") == null || object.get("user_id").getAsString() == null) ? ""
                : object.get("user_id").getAsString();
        String contact_id = (object.get("contact_id") == null || object.get("contact_id").getAsString() == null) ? ""
                : object.get("contact_id").getAsString();
        String response_description = "Success", response_code = "00";
        Set<Object> beneficiaries = null;
        if (contact_id.isEmpty()) {
            response_code = "01";
            response_description = "Missing Parameter!";
        } else {
            try {
                int contactId = Integer.parseInt(contact_id);
                beneficiaries = DAO.getBeneficiaries(contactId);
                if (beneficiaries == null) {
                    response_code = "99";
                    response_description = "An error occurred!";
                }
            } catch (NumberFormatException ex) {
                LOG.error("Error - NumberFormatException: " + ex.getMessage());
            }
        }
        return GenericCollectionResponse.builder().response_code(response_code).response_description(response_description).response_data(beneficiaries).build();
    }

    public GenericCollectionResponse getBanksInCountry(String request) {
        Set<Object> banks = new HashSet<>();
        String response_description = "Success", response_code = "00";
        if (request.isEmpty()) {
            LOG.error("getBanksInCountry - Error: UserID is null/empty");
        } else {
            banks = DAO.getBanksInCountry(request);
            if (banks == null) {
                response_code = "99";
                response_description = "An error occurred!";
            }
        }
        return GenericCollectionResponse.builder().response_code(response_code).response_description(response_description).response_data(banks).build();
    }

    public PostGenericResponse saveBeneficiary(String request) {
        JsonObject object = (JsonObject) new JsonParser().parse(request);
        String response_description = "Success", response_code = "00";
        Beneficiary b = null;
        String bank_id = (object.get("bank_id") == null || object.get("bank_id").getAsString() == null) ? ""
                : object.get("bank_id").getAsString();
        String account_number = (object.get("account_number") == null || object.get("account_number").getAsString() == null) ? ""
                : object.get("account_number").getAsString();
        String first_name = (object.get("first_name") == null || object.get("first_name").getAsString() == null) ? ""
                : object.get("first_name").getAsString();
        String last_name = (object.get("last_name") == null || object.get("last_name").getAsString() == null) ? ""
                : object.get("last_name").getAsString();
        String country_code = (object.get("country_code") == null || object.get("country_code").getAsString() == null) ? ""
                : object.get("country_code").getAsString();
        String sender_id = (object.get("sender_id") == null || object.get("sender_id").getAsString() == null) ? ""
                : object.get("sender_id").getAsString();

        if (bank_id.isEmpty() || account_number.isEmpty() || first_name.isEmpty() || last_name.isEmpty() || country_code.isEmpty()
                || sender_id.isEmpty()) {
            response_code = "01";
            response_description = "Missing Parameter!";
        } else {
            try {
                int bankId = Integer.parseInt(bank_id);
                int contactId = Integer.parseInt(sender_id);
                b = Beneficiary.builder().account_number(account_number).bank_id(bankId)
                        .country_code(country_code).first_name(first_name).last_name(last_name).sender_id(contactId).build();
                b = DAO.saveBeneficiary(b);
                if (b == null) {
                    response_code = "05";
                    response_description = "Beneficiary not saved.";
                }
            } catch (NumberFormatException e) {
                LOG.error("saveBeneficiary - NumberFormatException: " + e.getMessage());
                response_description = "User Error: Incorrect bank id [" + bank_id + "] or contact id [" + sender_id + "]";
                response_code = "99";
            }
        }

        return PostGenericResponse.builder().response_code(response_code).response_description(response_description).data(b).build();

    }

    public PostGenericResponse getBeneficiary(String request) {
        String response_description = "Success", response_code = "00";
        Beneficiary b = null;
        try {
            double benIdDbl = Double.parseDouble(request);
            int beneficiaryID = (int) benIdDbl;
            b = DAO.getBeneficiary(beneficiaryID);
        } catch (NumberFormatException ex) {
            LOG.error("getBeneficiary - NumberFormatError: " + ex.getMessage());
            response_code = "99";
            response_description = "Error: Could not format Beneficiary ID: " + request;
        }
        return PostGenericResponse.builder().response_code(response_code).response_description(response_description).data(b).build();
    }

    public PostGenericResponse login(String request) {
        JsonObject object = (JsonObject) new JsonParser().parse(request);
        String response_description = "Success", response_code = "00";
        User user = null;
//        String first_name = "", last_name = "", phone = "", userId = "";
        String email = (object.get("email") == null || object.get("email").getAsString() == null) ? ""
                : object.get("email").getAsString();
        String pass = (object.get("pass") == null || object.get("pass").getAsString() == null) ? ""
                : object.get("pass").getAsString();
        if (email.isEmpty() || pass.isEmpty()) {
            response_code = "01";
            response_description = "Missing Parameter!";
        } else {
            user = DAO.getUser(email);
            if (user != null) {
                String securePass = DigestUtils.md5Hex(pass);
                if (!user.getPassword().trim().equalsIgnoreCase(securePass)) {
                    response_code = "02";
                    response_description = "Invalid Password";
                    user = null;
                }
            } else {
                response_code = "03";
                response_description = "User not found!";
            }
        }

        return PostGenericResponse.builder().response_code(response_code).response_description(response_description).data(user).build();

//        return User.builder().response_code(response_code).response_description(response_description).email(email).first_name(first_name)
//                .last_name(last_name).phone(phone).id(userId).build();
    }

    public PostGenericResponse getBank(String request) {
        JsonObject object = (JsonObject) new JsonParser().parse(request);
        Bank bank = null;
        String response_description = "Success", response_code = "00";
        String country_code = (object.get("country_code") == null || object.get("country_code").getAsString() == null) ? ""
                : object.get("country_code").getAsString();
        String bank_id = (object.get("bank_id") == null || object.get("bank_id").getAsString() == null) ? ""
                : object.get("bank_id").getAsString();
        if (bank_id.isEmpty() || bank_id.isEmpty()) {
            response_code = "01";
            response_description = "Missing Parameter!";
        } else {
            try {
                int bankID = Integer.parseInt(bank_id);
                bank = DAO.getBank(country_code, bankID);
                if (bank == null) {
                    response_code = "06";
                    response_description = "Bank not found/An error occurred!";
                }
            } catch (NumberFormatException ex) {
                LOG.error("getBank - NumberFormatException: " + ex.getMessage());
                response_code = "99";
                response_description = "Error: Could not format Bank ID: " + bank_id;
            }
        }
        return PostGenericResponse.builder().response_code(response_code).response_description(response_description).data(bank).build();
    }

    public PostGenericResponse saveTransaction(String request) {
        JsonObject object = (JsonObject) new JsonParser().parse(request);
        Sale sale;
        String response_description = "Success", response_code = "00";
        String originator_id = (object.get("originator_id") == null || object.get("originator_id").getAsString() == null) ? ""
                : object.get("originator_id").getAsString();
        String beneficiary_id = (object.get("beneficiary_id") == null || object.get("beneficiary_id").getAsString() == null) ? ""
                : object.get("beneficiary_id").getAsString();
        String order_id = (object.get("order_id") == null || object.get("order_id").getAsString() == null) ? ""
                : object.get("order_id").getAsString();
        String currency = (object.get("currency") == null || object.get("currency").getAsString() == null) ? ""
                : object.get("currency").getAsString();
        String bank_name = (object.get("bank_name") == null || object.get("bank_name").getAsString() == null) ? ""
                : object.get("bank_name").getAsString();
        String beneficiary_account_no = (object.get("beneficiary_account_no") == null || object.get("beneficiary_account_no").getAsString() == null) ? ""
                : object.get("beneficiary_account_no").getAsString();
        String beneficiary_country = (object.get("beneficiary_country") == null || object.get("beneficiary_country").getAsString() == null) ? ""
                : object.get("beneficiary_country").getAsString();
        String receiving_amount = (object.get("receiving_amount") == null || object.get("receiving_amount").getAsString() == null) ? ""
                : object.get("receiving_amount").getAsString();
        String exchange_rate = (object.get("exchange_rate") == null || object.get("exchange_rate").getAsString() == null) ? ""
                : object.get("exchange_rate").getAsString();
        String sending_amount = (object.get("sending_amount") == null || object.get("sending_amount").getAsString() == null) ? ""
                : object.get("sending_amount").getAsString();
        String fee = (object.get("fee") == null || object.get("fee").getAsString() == null) ? ""
                : object.get("fee").getAsString();
        String total = (object.get("total") == null || object.get("total").getAsString() == null) ? ""
                : object.get("total").getAsString();

        double beneficiary_amount = Double.parseDouble(receiving_amount);
        double exchangeRate = Double.parseDouble(exchange_rate);
        double sendingAmount = Double.parseDouble(sending_amount);
        double feeDbl = Double.parseDouble(fee);
        double totalDbl = Double.parseDouble(total);

        sale = Sale.builder().bank_name(bank_name).beneficiary_account_no(beneficiary_account_no).beneficiary_country(beneficiary_country)
                .beneficiary_id(beneficiary_id).currency(currency).exchange_rate(exchangeRate).fee(feeDbl).order_id(order_id).originator_id(originator_id)
                .receiving_amount(beneficiary_amount).sending_amount(sendingAmount).total(totalDbl).build();

        sale = DAO.saveTransaction(sale);
        if (sale == null) {
            response_code = "99";
            response_description = "Sale not saved.";
        }

        return PostGenericResponse.builder().response_code(response_code).response_description(response_description).data(sale).build();
    }

    public GenericCollectionResponse getPendingTransactions(String request) {
        String response_code = "00", response_description = "Success";
        Set<Object> sales = DAO.getTransactionsWithStatus(request, "PENDING");
        if (sales == null) {
            response_code = "99";
            response_description = "An error occurred!";
        }
        return GenericCollectionResponse.builder().response_code(response_code).response_description(response_description)
                .response_data(sales).build();
    }

    public GenericCollectionResponse getAllTransaction(String request) {
        String response_code = "00", response_description = "Success";
        Set<Object> sales = DAO.getAllTransactions(request);
        if (sales == null) {
            response_code = "99";
            response_description = "An error occurred!";
        }
        return GenericCollectionResponse.builder().response_code(response_code).response_description(response_description)
                .response_data(sales).build();
    }

    public GenericCollectionResponse getSuccessfulTransactions(String request) {
        String response_code = "00", response_description = "Success";
        Set<Object> sales = DAO.getTransactionsWithStatus(request, "COMPLETED");
        if (sales == null) {
            response_code = "99";
            response_description = "An error occurred!";
        }
        return GenericCollectionResponse.builder().response_code(response_code).response_description(response_description)
                .response_data(sales).build();
    }

    public Object getFailedTransactions(String request) {
        String response_code = "00", response_description = "Success";
        Set<Object> sales = DAO.getTransactionsWithStatus(request, "FAILED");
        if (sales == null) {
            response_code = "99";
            response_description = "An error occurred!";
        }
        return GenericCollectionResponse.builder().response_code(response_code).response_description(response_description)
                .response_data(sales).build();
    }

    public Set<Bank> getBanks() {
        return DAO.getBanks();
    }

    public PostGenericResponse deleteBeneficiary(String request) {
        String response_code = "00", response_description = "Success";
        try {
            int benId = Integer.parseInt(request);
            boolean deleted = DAO.deleteBeneficiary(benId);
            if(!deleted){
                response_code = "99"; response_description = "Beneficiary Not Deleted";
            }
        } catch (NumberFormatException nfe) {
            LOG.error("Error in request parameter: "+nfe.getMessage());
            response_code = "07";
            response_description = "Invalid Parameter";
        }
        return PostGenericResponse.builder().response_code(response_code).response_description(response_description).build();
    }

}
