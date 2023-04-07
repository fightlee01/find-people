package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.beans.Rule;
import com.lee.beans.vo.AcceptRule;
import com.lee.beans.vo.CreateRule;
import com.lee.beans.vo.FindByRule;
import com.lee.exceptionhandler.FindPeopleException;
import com.lee.mapper.RuleMapper;
import com.lee.service.RuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-04-02
 */
@Service
public class RuleServiceImpl extends ServiceImpl<RuleMapper, Rule> implements RuleService {

    @Override
    public void createRuleToDatabase(CreateRule createRule) {
        Rule rule = new Rule();
        byte[] tmp;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(createRule.getRuleItems());
            tmp = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindPeopleException();
        }

        rule.setRuleName(createRule.getRuleName());
        rule.setRuleDes(createRule.getRuleDes());
        rule.setRule(tmp);
        baseMapper.insert(rule);
    }

    @Override
    public List<String> findOrganizationByRule(FindByRule findByRule) {
        // 按照提交的规则id列表依次查找规则
        List<String> ruleIds = findByRule.getRuleIds();
        for(String ids:ruleIds) {
            AcceptRule acceptRule = new AcceptRule();
            QueryWrapper<Rule> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", ids);
            Rule rule = baseMapper.selectOne(queryWrapper);
            List<Map<String, String>> ruleDetail = null;
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rule.getRule());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                ruleDetail = (List<Map<String, String>>) objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                throw new FindPeopleException();
            }
            for(Map<String, String> ruleItem: ruleDetail) {
                String field_name = ruleItem.get("field");
                String field_rule = ruleItem.get("rule");
                System.out.println(field_name+"===="+field_rule);
            }
            System.out.println(ruleDetail);
        }
        return null;
    }
}
