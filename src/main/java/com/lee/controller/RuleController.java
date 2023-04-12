package com.lee.controller;


import com.lee.beans.Rule;
import com.lee.beans.vo.CreateRule;
import com.lee.service.RuleService;
import com.lee.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lee
 * @since 2023-04-02
 */
@Api(description = "规则创建")
@RestController
@RequestMapping("/rule")
@CrossOrigin
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @ApiOperation("创建规则")
    @PostMapping("createRule")
    public R createRule(@ApiParam(value = "规则详情", required = true)
                        @RequestBody CreateRule createRule) {
        System.out.println(createRule.toString());
        ruleService.createRuleToDatabase(createRule);
        return R.ok().message("创建规则成功");
    }

    @ApiOperation("查找所有已有规则")
    @GetMapping("findExistRule")
    public R findExistRule() {
        List<Rule> rules = ruleService.list(null);
        List<Map<String, Object>> resultData = new ArrayList<>();
        for (Rule rule : rules) {
            Object result = null;
            Map<String, Object> map = new HashMap<>();
            map.put("ruleName", rule.getRuleName());
            map.put("ruleDes", rule.getRuleDes());
            map.put("id", rule.getId());
            byte[] rule1 = rule.getRule();
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rule1);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                result = objectInputStream.readObject();
                map.put("rule", result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultData.add(map);
        }
        return R.ok().data("rules", resultData);
    }

    @ApiOperation("根据id删除规则")
    @PostMapping("deleteRuleById/{id}")
    public R deleteRuleById(@PathVariable String id) {
        boolean isDeleted = ruleService.deleteById(id);
        if(isDeleted) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

}

