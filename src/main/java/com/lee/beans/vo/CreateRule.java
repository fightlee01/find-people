package com.lee.beans.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateRule implements Serializable {
    private String ruleName;
    private String ruleDes;
    private Object ruleItems;
}
