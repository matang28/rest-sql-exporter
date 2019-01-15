package com.matang28.restsqlexporter.utils;

import java.util.Map;

public class LabelFormat {

    public static String format(String pattern, Map<String, Object> args){
        final String[] p = {pattern};
        args.forEach((k,v)-> p[0] = p[0].replace("${" + k + "}",String.valueOf(v)));
        return p[0];
    }

}
