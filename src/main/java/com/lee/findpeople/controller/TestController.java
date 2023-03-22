package com.lee.findpeople.controller;

import com.lee.findpeople.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "测试api")
@RestController
@RequestMapping("/testapi")
public class TestController {

    @GetMapping
    @ApiOperation("测试swagger")
    public R testApi() {
        return R.ok();
    }
}
