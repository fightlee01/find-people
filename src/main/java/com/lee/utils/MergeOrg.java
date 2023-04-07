package com.lee.utils;

import java.util.*;

public class MergeOrg {
    public static Map<String, Integer> mergeOrgForTwoWay(Map<String, List<String>> map1, Map<String, List<String>> map2) {
        Map<String, Integer> result = new HashMap<>();
        List<String> listOrg = new ArrayList<>();
        // List<String> listFromOrganization = new ArrayList<>();
        Set<String> map1Key = map1.keySet();
        Set<String> map2Key = map2.keySet();
        for (String key : map1Key) {
            listOrg.addAll(map1.get(key));
        }
        for (String key : map2Key) {
            listOrg.addAll(map2.get(key));
        }
        listOrg.forEach(item -> result.compute(item, (k, v) -> v == null ? 1 : ++v));
        // listFromOrganization.forEach(name -> result.compute(name, (k, v) -> v == null ? 1 : ++v));
        return result;
    }
}
