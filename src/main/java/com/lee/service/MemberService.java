package com.lee.service;

import com.lee.beans.vo.AcceptRule;
import com.lee.beans.Member;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee
 * @since 2023-03-22
 */
public interface MemberService extends IService<Member> {

    Map<String, Float> getPossibleOrganizationFromMember(AcceptRule acceptRule);
}
