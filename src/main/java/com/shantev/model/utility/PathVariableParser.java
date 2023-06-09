package com.shantev.model.utility;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PathVariableParser {
    public static Map<String, String> getPathVariables(String pattern, String pathInfo) {
        Map<String, String> pathVariables = new LinkedHashMap<>();
        String[] pathVariableNames = pattern.split("/");
        String[] pathVariableData = pathInfo.split("/");
        if(pathVariableNames.length != pathVariableData.length) {
            throw new IllegalArgumentException("Illegal url");
        }

        for(int i = 1; i < pathVariableNames.length; i++) {
            pathVariables.put(pathVariableNames[i], pathVariableData[i]);
        }
        return pathVariables;
    }
}
