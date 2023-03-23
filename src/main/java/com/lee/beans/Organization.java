package com.lee.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lee
 * @since 2023-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Organization对象", description="")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Long id;

    @ApiModelProperty(value = "组织中文名")
    private String orgnameCn;

    @ApiModelProperty(value = "组织英文名")
    private String orgnameEn;

    @ApiModelProperty(value = "组织简写")
    private String orgnameAbbr;

    @ApiModelProperty(value = "组织别名")
    private String orgnameAlias;

    @ApiModelProperty(value = "组织类型")
    private String orgType;

    @ApiModelProperty(value = "组织状态")
    private String orgStatus;

    @ApiModelProperty(value = "组织成立日期")
    private String orgDate;

    @ApiModelProperty(value = "组织所在国家")
    private String orgCountry;

    @ApiModelProperty(value = "组织所在城市")
    private String orgCity;

    @ApiModelProperty(value = "组织地址")
    private String orgAddress;

    @ApiModelProperty(value = "组织官网")
    private String orgWebsite;

    @ApiModelProperty(value = "组织电话")
    private String orgPhone;

    @ApiModelProperty(value = "组织email")
    private String orgMail;

    private String orgDomain;

    private String orgLinkedin;

    private String orgFacebook;

    @ApiModelProperty(value = "组织介绍")
    private String orgIntroduction;

    @ApiModelProperty(value = "组织成员数")
    private Integer orgMembercount;


}
