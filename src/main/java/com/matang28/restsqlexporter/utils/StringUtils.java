package com.matang28.restsqlexporter.utils;

import java.util.StringJoiner;

public class StringUtils {

    public static boolean isEmpty(String str){
        return !notEmpty(str);
    }

    public static boolean notEmpty(String str){
        return null!=str && str.length()>0;
    }

    public static String joinNonEmpty(String delim, String... strings){

        StringJoiner joiner = new StringJoiner(delim);

        for(String s : strings){
            if(notEmpty(s))
                joiner.add(s);
        }

        return joiner.toString();
    }

}
