package com.lee.controller;

import com.lee.beans.AcceptRule;
import com.lee.service.MemberService;
import com.lee.service.OrganizationService;
import com.lee.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Map<String, Float>> possibleOrganizationFromMember = memberService.getPossibleOrganizationFromMember(acceptRule);
        List<Map<String, Float>> possibleOrganizationFromOrganization = organizationService.getPossibleOrganizationFromOrganization(acceptRule);

        return R.ok().data("org1", possibleOrganizationFromMember)
                .data("org2", possibleOrganizationFromOrganization);
    }
}
