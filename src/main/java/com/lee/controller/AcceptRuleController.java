package com.lee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.beans.vo.AcceptRule;
import com.lee.beans.Organization;
import com.lee.beans.vo.FindByRule;
import com.lee.service.MemberService;
import com.lee.service.OrganizationService;
import com.lee.service.RuleService;
import com.lee.utils.MergeOrg;
import com.lee.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "规则匹配")
@RestController
@RequestMapping("/find_people/accept_rule")
@CrossOrigin
public class AcceptRuleController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private RuleService ruleService;

    @ApiOperation("规则匹配")
    @PostMapping("find")
    public R findPeople(@ApiParam(name = "info", value = "规则信息", required = true)
                        @RequestBody List<AcceptRule> acceptRuleList) {
        Map<String, Map<String, Integer>> result = new HashMap<>();
        for(AcceptRule acceptRule:acceptRuleList) {
            Map<String, List<String>> possibleOrganizationFromMember = memberService.getPossibleOrganizationFromMember(acceptRule);
            Map<String, List<String>> possibleOrganizationFromOrganization = organizationService.getPossibleOrganizationFromOrganization(acceptRule);
            Map<String, Integer> resultItem = MergeOrg.mergeOrgForTwoWay(possibleOrganizationFromMember,
                    possibleOrganizationFromOrganization);
            result.put(acceptRule.getRuleId(), resultItem);
        }
        return R.ok().data("possibleOrg", result);
    }

    @ApiOperation("根据规则查找")
    @PostMapping("findByRule")
    public R findByRule(@ApiParam(name = "ruleId_inputValue", value = "规则id和输入", required = true)
                        @RequestBody FindByRule findByRule) {
        // System.out.println(findByRule.getRuleIds());
        // System.out.println(findByRule.getInputValue());
        List<String> result = ruleService.findOrganizationByRule(findByRule);
        return R.ok();
    }

    @ApiOperation("查询组织表所有国家用作下拉列表")
    @GetMapping("getAllCountry")
    public R getAllCountry() {
        QueryWrapper<Organization> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("org_country", "");
        List<Organization> list = organizationService.list(queryWrapper);
        List<String> result = new ArrayList<>();
        for(Organization organization:list) {
            result.add(organization.getOrgCountry().trim());
        }
        return R.ok().data("countries", result);
    }
}
