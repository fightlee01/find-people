package com.lee.service;

import com.lee.beans.Rule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.beans.vo.CreateRule;
import com.lee.beans.vo.FindByRule;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee
 * @since 2023-04-02
 */
public interface RuleService extends IService<Rule> {

    void createRuleToDatabase(CreateRule createRule);

    List<String> findOrganizationByRule(FindByRule findByRule);

    boolean deleteById(String id);
}
