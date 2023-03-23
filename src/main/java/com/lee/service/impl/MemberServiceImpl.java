package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.beans.AcceptRule;
import com.lee.beans.Member;
import com.lee.mapper.MemberMapper;
import com.lee.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-03-22
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public Map<String, Float> getPossibleOrganizationFromMember(AcceptRule acceptRule) {
        Map<String, Float> result = new HashMap<>();
        if(acceptRule.getName() != null) {
            String name = (String) acceptRule.getName().get("value");
            Double weight = (Double) acceptRule.getName().get("weight");
            this.getPossibleOrgViaName(result, name, weight.floatValue());
        }
        return result;
    }

    private void getPossibleOrgViaName(
            Map<String, Float> result,
            String name,
            Float weight) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("membername_en", name);
        List<Member> list = this.list(queryWrapper);
        for(Member member:list) {
            if(result.containsKey(member.getMemberOrginization())) {
                float originWeight = result.get(member.getMemberOrginization());
                result.replace(member.getMemberOrginization(), originWeight+weight);
            } else {
                result.put(member.getMemberOrginization(), weight);
            }
        }
    }
}
