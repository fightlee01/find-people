package com.lee.service;

import com.lee.beans.AcceptRule;
import com.lee.beans.Organization;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee
 * @since 2023-03-22
 */
public interface OrganizationService extends IService<Organization> {

    Map<String, Float> getPossibleOrganizationFromOrganization(AcceptRule acceptRule);
}
