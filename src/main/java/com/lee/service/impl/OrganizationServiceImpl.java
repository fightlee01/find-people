package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.beans.AcceptRule;
import com.lee.beans.Organization;
import com.lee.mapper.OrganizationMapper;
import com.lee.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-03-22
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Override
    public List<Map<String, Float>> getPossibleOrganizationFromOrganization(AcceptRule acceptRule) {
        List<Map<String, Float>> result = new ArrayList<>();
        if (acceptRule.getCountry() != null) {
            String country = (String) acceptRule.getCountry().get("value");
            Double weight = (Double) acceptRule.getCountry().get("weight");
            this.getPossibleOrgViaCountry(result, country, weight);
        }
        return result;
    }

    private void getPossibleOrgViaCountry(List<Map<String, Float>> result, String country, Double weight) {
        QueryWrapper<Organization> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("org_country", country);
        List<Organization> list = this.list(queryWrapper);
        for (Organization organization : list) {
            Map<String, Float> map = new HashMap<>();
            map.put(organization.getOrgnameEn(), weight.floatValue());
            result.add(map);
        }
    }
}
