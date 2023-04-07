package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.beans.vo.AcceptRule;
import com.lee.beans.Organization;
import com.lee.exceptionhandler.FindPeopleException;
import com.lee.mapper.OrganizationMapper;
import com.lee.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.lee.utils.ResultCode.PARAM_ERROR;

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
    public Map<String, List<String>> getPossibleOrganizationFromOrganization(AcceptRule acceptRule) {
        // 存储每个字段的结果，格式为：{"name": [], "phone": [], "email": [], ...}
        Map<String, List<String>> result = new HashMap<>();


        if (acceptRule.getCountry() != null && acceptRule.getCountry().get("value") != null) {
            try {
                String country = (String) acceptRule.getCountry().get("value");
                String rule = (String) acceptRule.getCountry().get("rule");
                this.getPossibleOrgViaCountry(result, country, rule);
            } catch (Exception e) {
                e.printStackTrace();
                throw new FindPeopleException(PARAM_ERROR, "参数传递错误！！！");
            }
        }
        if (acceptRule.getEmail() != null && acceptRule.getEmail().get("value") != null) {
            try {
                String email = (String) acceptRule.getEmail().get("value");
                String rule = (String) acceptRule.getEmail().get("rule");
                this.getPossibleOrgViaEmail(result, email, rule);
            } catch (Exception e) {
                e.printStackTrace();
                throw new FindPeopleException(PARAM_ERROR, "参数传递错误！！！");
            }
        }
        if (acceptRule.getPhone() != null && acceptRule.getPhone().get("value") != null) {
            try {
                String phone = (String) acceptRule.getPhone().get("value");
                String rule = (String) acceptRule.getPhone().get("rule");
                this.getPossibleOrgViaPhone(result, phone, rule);
            } catch (Exception e) {
                e.printStackTrace();
                throw new FindPeopleException(PARAM_ERROR, "参数传递错误！！！");
            }
        }
        return result;
    }

    private void getPossibleOrgViaPhone(Map<String, List<String>> result, String phone, String rule) {
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
        List<String> resultList = new ArrayList<>();
        resultList.add(maxSimilarityOrgName);
        result.put("phone", resultList);
    }

    private void getPossibleOrgViaEmail(Map<String, List<String>> result, String email, String rule) {
        List<String> excludeEmail = new ArrayList<>(Arrays.asList(
                "gmail.com", "qq.com", "163.com", "foxmail.com", "microsoft.com"));
        email = email.split("@")[1];
        // 判断是否需要排除公用邮箱，如果传入的是公用邮箱且用户需要排除公用邮箱时结束程序
        if (rule.equals("true") && excludeEmail.contains(email)) {
            return;
        }
        // 查找的邮箱不是公用邮箱或者用户不排除公用邮箱，继续查找
        List<Organization> list = this.list(null);
        List<String> resultList = new ArrayList<>();
        for (Organization organization : list) {
            boolean contains = organization.getOrgMail().contains(email);
            if (contains && !organization.getOrgnameEn().equals("")) {
                resultList.add(organization.getOrgnameEn());
            }
        }
        result.put("email", resultList);
    }

    private void getPossibleOrgViaCountry(Map<String, List<String>> result, String country, String rule) {
        QueryWrapper<Organization> queryWrapper = new QueryWrapper<>();
        // 判断是精确查找还是模糊查找
        if(rule.equals("精确查找")) {
            queryWrapper.eq("org_country", country);
        } else {
            queryWrapper.like("org_country", country);
        }
        System.out.println(country);
        List<Organization> list = this.list(queryWrapper);
        List<String> countryList = new ArrayList<>();
        for (Organization organization : list) {
            countryList.add(organization.getOrgnameEn());
        }
        result.put("country", countryList);
    }
}
