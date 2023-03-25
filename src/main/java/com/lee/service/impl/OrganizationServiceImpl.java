package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.beans.AcceptRule;
import com.lee.beans.Organization;
import com.lee.mapper.OrganizationMapper;
import com.lee.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Map<String, Float> getPossibleOrganizationFromOrganization(AcceptRule acceptRule) {
        Map<String, Float> result = new HashMap<>();
        if (acceptRule.getCountry().get("value") != null) {
            String country = (String) acceptRule.getCountry().get("value");
            Double weight = (Double) acceptRule.getCountry().get("weight");
            this.getPossibleOrgViaCountry(result, country, weight.floatValue());
        }
        if (acceptRule.getEmail().get("value") != null) {
            String email = (String) acceptRule.getEmail().get("value");
            Double weight = (Double) acceptRule.getEmail().get("weight");
            this.getPossibleOrgViaEmail(result, email, weight.floatValue());
        }
        if (acceptRule.getPhone().get("value") != null) {
            String phone = (String) acceptRule.getPhone().get("value");
            Double weight = (Double) acceptRule.getPhone().get("weight");
            this.getPossibleOrgViaPhone(result, phone, weight.floatValue());
        }
        return result;
    }

    private void getPossibleOrgViaPhone(Map<String, Float> result, String phone, Float weight) {
        List<Organization> list = this.list(null);
        String maxSimilarityOrgName = "";
        double maxSimilarity = 0d;
        for (Organization organization : list) {
            String orgPhone = organization.getOrgPhone().trim();
            double jaroWinklerDistance = StringUtils.getJaroWinklerDistance(phone, orgPhone);
            if(jaroWinklerDistance > maxSimilarity) {
                maxSimilarity = jaroWinklerDistance;
                maxSimilarityOrgName = organization.getOrgnameEn();
            }
        }
        if(result.containsKey(maxSimilarityOrgName)) {
            float originWeight = result.get(maxSimilarityOrgName);
            result.replace(maxSimilarityOrgName, originWeight+weight);
        } else {
            result.put(maxSimilarityOrgName, weight);
        }
    }

    private void getPossibleOrgViaEmail(Map<String, Float> result, String email, Float weight) {
        List<String> excludeEmail = new ArrayList<>(Arrays.asList(
                "gmail.com", "qq.com", "163.com", "foxmail.com", "microsoft.com"));
        email = email.split("@")[1];
        if (excludeEmail.contains(email)) {
            weight = weight / 10;
        }
        List<Organization> list = this.list(null);
        for (Organization organization : list) {
            boolean contains = organization.getOrgMail().contains(email);
            if (contains && !organization.getOrgnameEn().equals("")) {
                if(result.containsKey(organization.getOrgnameEn())) {
                    float originWeight = result.get(organization.getOrgnameEn());
                    result.replace(organization.getOrgnameEn(), originWeight+weight);
                } else {
                    result.put(organization.getOrgnameEn(), weight);
                }

            }
        }
    }

    private void getPossibleOrgViaCountry(Map<String, Float> result, String country, Float weight) {
        QueryWrapper<Organization> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("org_country", country);
        List<Organization> list = this.list(queryWrapper);
        for (Organization organization : list) {
            if(result.containsKey(organization.getOrgnameEn())) {
                float originWeight = result.get(organization.getOrgnameEn());
                result.replace(organization.getOrgnameEn(), originWeight+weight);
            } else {
                result.put(organization.getOrgnameEn(), weight);
            }
        }
    }
}
