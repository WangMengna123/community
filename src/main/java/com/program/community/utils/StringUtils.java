package com.program.community.utils;

public class StringUtils {
    public static boolean isNotNullOrEmpty(String str){
        if (str == null || str == ""){
            return false;
        }
        return true;
    }
}
