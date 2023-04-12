package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.beans.vo.AcceptRule;
import com.lee.beans.Member;
import com.lee.exceptionhandler.FindPeopleException;
import com.lee.mapper.MemberMapper;
import com.lee.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public Map<String, List<String>> getPossibleOrganizationFromMember(AcceptRule acceptRule) {
        Map<String, List<String>> result = new HashMap<>();
        if (acceptRule.getName() != null && acceptRule.getName().get("value") != null) {
            try {
                String rules = (String) acceptRule.getName().get("rule");
                String name = (String) acceptRule.getName().get("value");
                this.getPossibleOrgViaName(result, name, rules);
            } catch (Exception e) {
                e.printStackTrace();
                throw new FindPeopleException(PARAM_ERROR, "参数传递错误！！！");
            }
        }
        return result;
    }

    private void getPossibleOrgViaName(
            Map<String, List<String>> result,
            String name,
            String rules) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        List<String> resultList = new ArrayList<>();
        if (rules.equals("精确匹配")) {
            queryWrapper.eq("membername_en", name);
        } else {
            queryWrapper.like("membername_en", name);
        }
        List<Member> list = this.list(queryWrapper);
        for (Member member : list) {
            if(member.getMemberOrginization() != null) {
                resultList.add(member.getMemberOrginization());
            }
        }
        result.put("name", resultList);
    }
}
