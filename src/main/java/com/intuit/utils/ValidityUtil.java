package com.intuit.utils;

public class ValidityUtil {


    public static Boolean isValidString(String email){
        if(email == null){
            return false;
        }
        return true;
    }
}
