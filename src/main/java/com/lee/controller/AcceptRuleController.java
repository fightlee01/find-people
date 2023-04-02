package com.lee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.beans.vo.AcceptRule;
import com.lee.beans.Organization;
import com.lee.service.MemberService;
import com.lee.service.OrganizationService;
import com.lee.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(description = "规则匹配")
@RestController
@RequestMapping("/find_people/accept_rule")
public class AcceptRuleController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrganizationService organizationService;

    @ApiOperation("规则匹配")
    @PostMapping("find")
    public R findPeople(@ApiParam(name = "info", value = "规则信息", required = true)
                        @RequestBody AcceptRule acceptRule) {
        Map<String, Float> possibleOrganizationFromMember = memberService.getPossibleOrganizationFromMember(acceptRule);
        Map<String, List<String>> possibleOrganizationFromOrganization = organizationService.getPossibleOrganizationFromOrganization(acceptRule);
        // Map<String, Float> result = MergeOrg.mergeOrgForTwoWay(possibleOrganizationFromMember,
        //         possibleOrganizationFromOrganization);
        return R.ok().data("possibleOrg", possibleOrganizationFromOrganization);
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
