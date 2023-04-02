package com.lee.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindPeopleException extends RuntimeException {
    @ApiModelProperty(value = "自定义状态码")
    private Integer code;
    @ApiModelProperty(value = "自定义错误信息")
    private String msg;
}
