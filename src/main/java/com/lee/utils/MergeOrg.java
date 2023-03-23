package com.lee.utils;

import java.util.*;

public class MergeOrg {
    public static Map<String, Float> mergeOrgForTwoWay(Map<String, Float> map1, Map<String, Float> map2) {
        Set<String> map1Key = map1.keySet();
        for(String key:map1Key) {
            if(map2.containsKey(key)) {
                map2.replace(key, map1.get(key)+map2.get(key));
            } else {
                map2.put(key, map1.get(key));
            }
        }
        List<Map.Entry<String, Float>> list = new ArrayList<>(map2.entrySet());
        list.sort(new Comparator<Object>() {
            @SuppressWarnings("unchecked")
            public int compare(Object o1, Object o2) {
                return ((Comparable<Float>) ((Map.Entry<String, Float>) (o2)).getValue()).compareTo(((Map.Entry<String, Float>) (o1)).getValue());
            }
        });
        Map<String, Float> result = new LinkedHashMap<>();
        for (Map.Entry<String, Float> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
