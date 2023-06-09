package com.lee.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MergeOrg {
    public static Map<String, Integer> mergeOrgForTwoWay(Map<String, List<String>> map1, Map<String, List<String>> map2) {
        Map<String, Integer> result = new HashMap<>();
        List<String> listOrg = new ArrayList<>();
        Set<String> map1Key = map1.keySet();
        Set<String> map2Key = map2.keySet();
        for (String key : map1Key) {
            listOrg.addAll(map1.get(key));
        }
        for (String key : map2Key) {
            listOrg.addAll(map2.get(key));
        }
        listOrg.forEach(item -> result.compute(item, (k, v) -> v == null ? 1 : ++v));
        return result.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public static List<String> orgIntersection(Map<String, Map<String, Integer>> result) {
        List<String> resultIntersection = new ArrayList<>();
        for (String next : result.keySet()) {
            resultIntersection.addAll(result.get(next).keySet());
        }
        Map<String, Integer> map = resultIntersection.stream()
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
        List<String> resultList = new ArrayList<>();
        for(String next:map.keySet()) {
            if(map.get(next) > 1) {
                resultList.add(next);
            }
        }
        return resultList;
    }
}
