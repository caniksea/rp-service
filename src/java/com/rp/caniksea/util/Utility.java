/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rp.caniksea.util;

import java.util.Date;
import java.util.Random;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 *
 * @author caniksea
 */
public class Utility {
    
    private static final String RPCODE = "RPLS";
    public static final String RP_MERCHANTCODE = "RPSU";
    public static final String RP_USERPREFIX = "RPsC";
    
    public static String generateRPTransactionID(){
        return RPCODE + DateFormatUtils.format(new Date(), "yyMMddHHmmss") + generateRandom(12);
    }
    
    public static long generateRandom(int length){
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public static String generateUserID() {
        return RP_USERPREFIX + DateFormatUtils.format(new Date(), "yyMMddHHmmss") + generateRandom(12);
    }
    
}
