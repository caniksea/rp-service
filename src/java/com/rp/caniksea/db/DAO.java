/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rp.caniksea.db;

import com.rp.caniksea.model.Bank;
import com.rp.caniksea.model.Beneficiary;
import com.rp.caniksea.model.Country;
import com.rp.caniksea.model.PaymentMethod;
import com.rp.caniksea.model.Sale;
import com.rp.caniksea.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author caniksea
 */
public class DAO {

    private static final Logger LOG = Logger.getLogger(DAO.class);

    public Set<Country> getCountries() {
        Set<Country> countries = new HashSet<>();
        Connection con = DBConnection.getMySQLConnection();
        if (con != null) {
            String selectSQL = "SELECT * FROM country_master";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(selectSQL);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Country country = getCountryFromRS(rs);
                    countries.add(country);
                }
            } catch (SQLException ex) {
                LOG.error("getCountries - SQLException: " + ex.getMessage());
//                java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closePS(ps);
                closeRS(rs);
                closeCon(con);
            }
        }
        return countries;
    }

    private void closePS(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void closeRS(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void closeCon(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Set<PaymentMethod> getPaymentMethods() {
        Connection con = DBConnection.getMySQLConnection();
        Set<PaymentMethod> paymentMethods = new HashSet<>();
        if (con != null) {
            String selectSQL = "SELECT * FROM rp_pay_method";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(selectSQL);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PaymentMethod paymentMethod = getPaymethodFromRS(rs);
                    paymentMethods.add(paymentMethod);
                }
            } catch (SQLException ex) {
                LOG.error("SQLException: " + ex.getMessage());
//                java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closePS(ps);
                closeRS(rs);
                closeCon(con);
            }
        }
        return paymentMethods;
    }

    public Set<PaymentMethod> getPaymethod(int method) {
        Connection con = DBConnection.getMySQLConnection();
        Set<PaymentMethod> paymentMethods = new HashSet<>();
        if (con != null) {
            String selectSQL = "SELECT * FROM rp_pay_method WHERE id = ?";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(selectSQL);
                ps.setInt(1, method);
                rs = ps.executeQuery();
                if (rs.next()) {
                    PaymentMethod paymentMethod = getPaymethodFromRS(rs);
                    paymentMethods.add(paymentMethod);
                }
            } catch (SQLException ex) {
                LOG.error("getPaymethod - SQLException: " + ex.getMessage());
//                java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closePS(ps);
                closeRS(rs);
                closeCon(con);
            }
        }
        return paymentMethods;

    }

    public Set<Country> getCountry(String id) {
        Set<Country> countries = new HashSet<>();
        Connection con = DBConnection.getMySQLConnection();
        if (con != null) {
            String selectSQL = "SELECT * FROM country_master WHERE country_iso_code = ?";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(selectSQL);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Country country = getCountryFromRS(rs);
                    countries.add(country);
                }
            } catch (SQLException ex) {
                LOG.error("getCountry - SQLException: " + ex.getMessage());
//                java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeRS(rs); closePS(ps); closeCon(con);
            }

        }
        return countries;
    }

    private Country getCountryFromRS(ResultSet rs) throws SQLException {
        String iso_code = rs.getString("country_iso_code");
        String name = rs.getString("country_name");
        String currency = rs.getString("country_currency");
        String dialing_code = rs.getString("dialing_code");
        String flag_path = rs.getString("flag_path");
        double exchange_rate = rs.getDouble("exchange_rate");
        double dynamic_fee = rs.getDouble("dynamic_fee");
        double static_fee = rs.getDouble("static_fee");
        Country country = Country.builder()
                .country_currency(currency)
                .country_iso_code(iso_code)
                .country_name(name)
                .dialing_code(dialing_code)
                .dynamic_fee(dynamic_fee)
                .exchange_rate(exchange_rate)
                .static_fee(static_fee)
                .flag_path(flag_path).build();
        return country;
    }

    private PaymentMethod getPaymethodFromRS(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String method_name = rs.getString("method_name");
        PaymentMethod paymentMethod = PaymentMethod.builder().id(id).method_name(method_name).build();
        return paymentMethod;
    }

//    public TransactionPayload initiateSendMoney(TransactionPayload transactionPayload) {
//        Connection con = DBConnection.getMySQLConnection();
//        TransactionPayload tp = null;
//        if(con != null){
//            String insertSQL = "INSERT INTO rp_transaction (id, destination, rp_pay_method_id, source_amount, destination_amount, fee, total) "
//                    + "VALUES (?,?,?,?,?,?,?)";
//            PreparedStatement ps = null;
////            ResultSet rs = null;
//            try {
//                ps = con.prepareStatement(insertSQL);
//                ps.setString(1, transactionPayload.getRp_transaction_id());
//                ps.setString(2, transactionPayload.getDestination_country_iso_code());
//                ps.setInt(3, transactionPayload.getPay_method());
//                ps.setDouble(4, transactionPayload.getSource_amount());
//                ps.setDouble(5, transactionPayload.getDestination_amount());
//                ps.setDouble(6, transactionPayload.getFee());
//                ps.setDouble(7, transactionPayload.getTotal());
//                ps.executeUpdate();
//                tp = transactionPayload;
//            } catch (SQLException ex) {
//                LOG.error("initiateSendMoney - SQLException: " + ex.getMessage());
////                java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                closePS(ps); closeCon(con);
//            }
//        }
//        return tp;
//    }

//    public Set<RPTransactionEvent> createTransactionEvent(RPTransactionEvent event) {
//        Connection con = DBConnection.getMySQLConnection();
//        Set<RPTransactionEvent> events = new HashSet<>();
//        if(con != null){
//            String insertSQL = "INNSERT INTO rp_transaction_event (rp_transaction_id, status, description, rp_user_id) VALUES (?,?,?,?)";
//            PreparedStatement ps = null;
//            try {
//                ps = con.prepareStatement(insertSQL);
//                ps.setString(1, event.getRp_transaction_id());
//                ps.setString(2, event.getStatus());
//                ps.setString(3, event.getDescription());
//                ps.setString(4, event.getRp_transaction_id());
//                ps.executeUpdate();
//                events.add(event);
//            } catch (SQLException ex) {
//                LOG.error("createTransactionEvent - SQLException: "+ex.getMessage());
////                java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                closePS(ps); closeCon(con);
//            }
//        }
//        return events;
//    }

    public User getUser(String l_customer_email) {
        Connection con = DBConnection.getMySQLConnection();
        User user = null;
        if(con != null){
            String selectSQL = "SELECT * FROM contact_master WHERE email = ?";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(selectSQL);
                ps.setString(1, l_customer_email);
                rs = ps.executeQuery();
                if(rs.next()){
                    String userId = rs.getString("userid");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("surname");
                    String phone = rs.getString("phone");
                    int contactId = rs.getInt("contactid");
                    user = User.builder()
                            .email(email)
                            .first_name(firstName)
                            .user_id(userId)
                            .last_name(lastName)
                            .password(password)
                            .phone(phone).contact_id(contactId).build();
                }
            } catch (SQLException ex) {
                LOG.error("getUser - SQLException: "+ex.getMessage());
//                java.util.logging.Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closePS(ps);
                closeRS(rs);
                closeCon(con);
            }
        }
        return user;
    }

    public User createUser(User u) {
        Connection con = DBConnection.getMySQLConnection();
        User createdUser = null;
        if(con != null){
            String insertSQL = "INSERT INTO contact_master (firstname, surname, email, userid, contactstatus, password, is_onlinecustomer, activation_key, fe_update_acc) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, u.getFirst_name());
                ps.setString(2, u.getLast_name());
                ps.setString(3, u.getEmail());
                ps.setString(4, u.getUser_id());
                ps.setString(5, u.getStatus());
                ps.setString(6, u.getPassword());
                ps.setString(7, u.getIs_onlinecustomer());
                ps.setString(8, u.getActivation_key());
                ps.setString(9, u.getFe_update_acc());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int contact_id = rs.getInt(1);
                    createdUser = User.builder().first_name(u.getFirst_name()).last_name(u.getLast_name()).email(u.getEmail())
                            .user_id(u.getUser_id()).status(u.getStatus()).contact_id(contact_id).build();
                }else{
                    LOG.info("User not created - User ID: "+u.getUser_id());
                }
            } catch (SQLException ex) {
                LOG.error("createUser - SQLException: "+ex.getMessage());
            } finally {
                closeRS(rs); closePS(ps); closeCon(con);
            }
        }
        return createdUser;
    }

    public Set<Object> getBeneficiaries(int contactId) {
        Connection con = DBConnection.getMySQLConnection();
        Set<Object> objects = null;
        if(con != null){
            String selectSQL = "SELECT * FROM ben_master WHERE contactid = ?";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(selectSQL);
                ps.setInt(1, contactId);
                rs = ps.executeQuery();
                objects = new HashSet<>();
                while(rs.next()){
                    int beneficiary_id = rs.getInt("benid");
                    int bank_id = rs.getInt("bankname");
                    String bank_branch = rs.getString("branchname");
                    String account_number = rs.getString("accountno");
                    String first_name = rs.getString("firstname");
                    String middle_name = rs.getString("middlename");
                    String last_name = rs.getString("lastname");
                    String country_code = rs.getString("countrycode");
                    Beneficiary b = Beneficiary.builder().beneficiary_id(beneficiary_id).bank_id(bank_id)
                            .bank_branch(bank_branch).account_number(account_number).first_name(first_name)
                            .middle_name(middle_name).last_name(last_name).sender_id(contactId).country_code(country_code).build();
                    objects.add(b);
                }
            } catch (SQLException ex) {
                LOG.error("getBeneficiaries - SQLException: "+ex.getMessage());
            } finally {
                closeRS(rs); closePS(ps); closeCon(con);
            }
        }
        return objects;
    }

    public Set<Object> getBanksInCountry(String request) {
        Connection con = DBConnection.getMySQLConnection();
        Set<Object> objects = null;
        if(con != null){
            String selectSQL = "SELECT * FROM bank_master WHERE country_iso_code = ?";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(selectSQL);
                ps.setString(1, request);
                rs = ps.executeQuery();
                objects = new HashSet<>();
                while(rs.next()){
                    int bank_id = rs.getInt("bankid");
                    String bank_name = rs.getString("bankname");
                    String country_code = rs.getString("country_iso_code");
                    String bank_code = rs.getString("bank_code");
                    String bank_alias = rs.getString("bank_alias_name");
                    Bank b = Bank.builder().bank_id(bank_id).bank_name(bank_name).bank_code(bank_code).bank_alias(bank_alias).country_code(country_code).build();
                    objects.add(b);
                }
            } catch (SQLException ex) {
                LOG.error("getBanksInCountry - SQLException: "+ex.getMessage());
            } finally {
                closeRS(rs); closePS(ps); closeCon(con);
            }
        }
        return objects;
    }

    public Beneficiary saveBeneficiary(Beneficiary b) {
        Beneficiary beneficiary = null;
        Connection con = DBConnection.getMySQLConnection();
        if(con != null){
            String insertSQL = "INSERT INTO ben_master (bankname, accountno, contactid, firstname, lastname, countrycode) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, b.getBank_id());
                ps.setString(2, b.getAccount_number());
                ps.setInt(3, b.getSender_id());
                ps.setString(4, b.getFirst_name());
                ps.setString(5, b.getLast_name());
                ps.setString(6, b.getCountry_code());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int benId = rs.getInt(1);
                    beneficiary = Beneficiary.builder().account_number(b.getAccount_number()).bank_id(b.getBank_id())
                            .beneficiary_id(benId).sender_id(b.getSender_id()).first_name(b.getFirst_name()).last_name(b.getLast_name())
                            .country_code(b.getCountry_code()).build();
                }else{
                    LOG.info("Beneficiary not created");
                }
            } catch (SQLException ex) {
                LOG.error("saveBeneficiary - SQLException: "+ex.getMessage());
            } finally {
                closeRS(rs); closePS(ps); closeCon(con);
            }
        }
        return beneficiary;
    }

    public Beneficiary getBeneficiary(int request) {
        Beneficiary beneficiary = null;
        Connection con = DBConnection.getMySQLConnection();
        if(con != null){
            String insertSQL = "SELECT * FROM ben_master WHERE benid = ?"; 
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(insertSQL);
                ps.setInt(1, request);
                rs = ps.executeQuery();
                if(rs.next()){
                    int beneficiary_id = rs.getInt("benid");
                    int bank_id = rs.getInt("bankname");
                    String bank_branch = rs.getString("branchname");
                    String account_number = rs.getString("accountno");
                    String first_name = rs.getString("firstname");
                    String middle_name = rs.getString("middlename");
                    String last_name = rs.getString("lastname");
                    int sender_id = rs.getInt("contactid");
                    String country_code = rs.getString("countrycode");
                    beneficiary = Beneficiary.builder().beneficiary_id(beneficiary_id).bank_id(bank_id)
                            .bank_branch(bank_branch).account_number(account_number).first_name(first_name)
                            .middle_name(middle_name).last_name(last_name).sender_id(sender_id).country_code(country_code).build();
                }
            } catch (SQLException ex) {
                LOG.error("saveBeneficiary - SQLException: "+ex.getMessage());
            } finally {
                closeRS(rs); closePS(ps); closeCon(con);
            }
        }
        return beneficiary;
    }

    public Bank getBank(String country_code, int bankID) {
        Bank bank = null;
        Connection con = DBConnection.getMySQLConnection();
        if(con != null){
            String insertSQL = "SELECT * FROM bank_master WHERE bankid = ? AND country_iso_code = ?"; 
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(insertSQL);
                ps.setInt(1, bankID);
                ps.setString(2, country_code);
                rs = ps.executeQuery();
                if(rs.next()){
//                    int bank_id = rs.getInt("bankid");
                    String bank_name = rs.getString("bankname");
//                    String countryCode = rs.getString("country_iso_code");
                    String bank_code = rs.getString("bank_code");
                    String bank_alias = rs.getString("bank_alias_name");
                    bank = Bank.builder().bank_alias(bank_alias).bank_code(bank_code).bank_id(bankID).bank_name(bank_name).country_code(country_code).build();
                }
            } catch (SQLException ex) {
                LOG.error("saveBeneficiary - SQLException: "+ex.getMessage());
            } finally {
                closeRS(rs); closePS(ps); closeCon(con);
            }
        }
        return bank;
    }

    public Sale saveTransaction(Sale sale) {
        Sale s = null;
        Connection con = DBConnection.getMySQLConnection();
        if(con != null){
            String insertSQL = "INSERT INTO sales_master (contactid, benid, orderdate, orderid, currency, bankname, accountno, benamount, "
                    + "exchangerate, orderamount, orderstatus, ordercountry, totalamount, buying_rate, profit_loss, agentaccountcardno) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                java.sql.Date date = new java.sql.Date(new Date().getTime());
                ps = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, sale.getOriginator_id());
                ps.setString(2, sale.getBeneficiary_id());
                ps.setDate(3, date);
                ps.setString(4, sale.getOrder_id());
                ps.setString(5, sale.getCurrency());
                ps.setString(6, sale.getBank_name());
                ps.setString(7, sale.getBeneficiary_account_no());
                ps.setDouble(8, sale.getReceiving_amount());
                ps.setDouble(9, sale.getExchange_rate());
                ps.setDouble(10, sale.getSending_amount());
                ps.setString(11, "PENDING");
                ps.setString(12, sale.getBeneficiary_country());
                ps.setDouble(13, sale.getTotal());
                ps.setDouble(14, sale.getExchange_rate());
                ps.setDouble(15, 0);
                ps.setString(16, "NO-IDEA");
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int saleId = rs.getInt(1);
                    s = Sale.builder().copy(sale).sale_id(saleId).order_date(new Date()).build();                    
                }else{
                    LOG.info("Sale not created");
                }
            } catch (SQLException ex) {
                LOG.error("saveTransaction - SQLException: "+ex.getMessage());
            } finally {
                closeRS(rs); closePS(ps); closeCon(con);
            }
        }
        return s;
    }

    public Set<Object> getPendingTransactions(String request) {
        Set<Object> sales = null;
        Connection con = DBConnection.getMySQLConnection();
        if(con != null){
            String selectSQL = "SELECT * FROM sales_master WHERE contactid = ? AND orderstatus = ? ORDER BY orderdate DESC";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(selectSQL);
                ps.setString(1, request);
                ps.setString(2, "PENDING");
                rs = ps.executeQuery();
                sales = new LinkedHashSet<>();
                while(rs.next()){
                    int saleId = rs.getInt("saleid");
                    String benId = rs.getString("benid");
                    Date date = rs.getDate("orderdate");
                    String transactionId = rs.getString("orderid");
                    String currency = rs.getString("currency");
                    String bankName = rs.getString("bankname");
                    String accountNo = rs.getString("accountno");
                    double receivingAmount = rs.getDouble("benamount");
                    double exchangeRate = rs.getDouble("exchangerate");
                    double sendingAmount = rs.getDouble("orderamount");
                    String country = rs.getString("ordercountry");
                    double totalAmount = rs.getDouble("totalamount");
                    String status = rs.getString("orderstatus");
                    Sale sale = Sale.builder().bank_name(bankName).beneficiary_account_no(accountNo).beneficiary_country(country)
                            .beneficiary_id(benId).currency(currency).exchange_rate(exchangeRate).order_date(date).order_id(transactionId)
                            .originator_id(request).receiving_amount(receivingAmount).sale_id(saleId).sending_amount(sendingAmount)
                            .total(totalAmount).status(status).build();
                    sales.add(sale);
                }
            } catch (SQLException ex) {
                LOG.error("getPendingTransactions - SQLException: "+ex.getMessage());
            }finally {
                closeRS(rs); closePS(ps); closeCon(con);
            }
        }        
        return sales;
    }

    public Set<Object> getAllTransactions(String request) {
        Set<Object> sales = null;
        Connection con = DBConnection.getMySQLConnection();
        if(con != null){
            String selectSQL = "SELECT * FROM sales_master WHERE contactid = ? ORDER BY orderdate DESC";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = con.prepareStatement(selectSQL);
                ps.setString(1, request);
                rs = ps.executeQuery();
                sales = new LinkedHashSet<>();
                while(rs.next()){
                    int saleId = rs.getInt("saleid");
                    String benId = rs.getString("benid");
                    Date date = rs.getDate("orderdate");
                    String transactionId = rs.getString("orderid");
                    String currency = rs.getString("currency");
                    String bankName = rs.getString("bankname");
                    String accountNo = rs.getString("accountno");
                    double receivingAmount = rs.getDouble("benamount");
                    double exchangeRate = rs.getDouble("exchangerate");
                    double sendingAmount = rs.getDouble("orderamount");
                    String country = rs.getString("ordercountry");
                    double totalAmount = rs.getDouble("totalamount");
                    Sale sale = Sale.builder().bank_name(bankName).beneficiary_account_no(accountNo).beneficiary_country(country)
                            .beneficiary_id(benId).currency(currency).exchange_rate(exchangeRate).order_date(date).order_id(transactionId)
                            .originator_id(request).receiving_amount(receivingAmount).sale_id(saleId).sending_amount(sendingAmount)
                            .total(totalAmount).build();
                    sales.add(sale);
                }
            } catch (SQLException ex) {
                LOG.error("getPendingTransactions - SQLException: "+ex.getMessage());
            }finally {
                closeRS(rs); closePS(ps); closeCon(con);
            }
        }        
        return sales;
    }

}
