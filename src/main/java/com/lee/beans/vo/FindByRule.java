package com.lee.beans.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FindByRule {
    private Map<String, Object> inputValue;
    private List<String> ruleIds;
}
