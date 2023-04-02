package com.lee.beans.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AcceptRule {
    private Map<String, Object> name;
    private Map<String, Object> email;
    private Map<String, Object> phone;
    private Map<String, Object> country;
    private Map<String, Object> city;
}
