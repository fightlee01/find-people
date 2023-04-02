package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.beans.vo.AcceptRule;
import com.lee.beans.Member;
import com.lee.exceptionhandler.FindPeopleException;
import com.lee.mapper.MemberMapper;
import com.lee.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public Map<String, Float> getPossibleOrganizationFromMember(AcceptRule acceptRule) {
        Map<String, Float> result = new HashMap<>();
        if (acceptRule.getName().get("value") != null) {
            try {
                String rules = (String) acceptRule.getName().get("rules");
                String name = (String) acceptRule.getName().get("value");
                this.getPossibleOrgViaName(result, name, rules);
            } catch (Exception e) {
                throw new FindPeopleException(PARAM_ERROR, "参数传递错误！！！");
            }
        }
        return result;
    }

    private void getPossibleOrgViaName(
            Map<String, Float> result,
            String name,
            String rules) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        if (rules.equals("精确匹配")) {
            queryWrapper.eq("membername_en", name);
        } else {
            queryWrapper.like("membername_en", name);
        }
        List<Member> list = this.list(queryWrapper);
        for (Member member : list) {
            if (result.containsKey(member.getMemberOrginization())) {
                Float originWeight = result.get(member.getMemberOrginization());
                result.replace(member.getMemberOrginization(), originWeight + 1);
            } else {
                result.put(member.getMemberOrginization(), 1f);
            }
        }
    }
}
