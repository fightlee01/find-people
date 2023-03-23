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
@ApiModel(value="Member对象", description="")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Long id;

    @ApiModelProperty(value = "成员名称")
    private String membernameEn;

    @ApiModelProperty(value = "成员所属组织")
    private String memberOrginization;

    @ApiModelProperty(value = "成员性别")
    private String memberGender;

    @ApiModelProperty(value = "成员婚姻状况")
    private String memberMarriage;

    @ApiModelProperty(value = "成员国家")
    private String memberCountry;

    @ApiModelProperty(value = "成员党派")
    private String memberParty;

    @ApiModelProperty(value = "成员宗教")
    private String memberReligion;

    @ApiModelProperty(value = "成员职位")
    private String memberPosition;

    @ApiModelProperty(value = "成员教育经历")
    private String memberEducation;

    @ApiModelProperty(value = "成员电话")
    private String memberPhone;

    @ApiModelProperty(value = "成员邮箱")
    private String memberMail;

    @ApiModelProperty(value = "成员地址")
    private String memberAddress;

    @ApiModelProperty(value = "成员领英")
    private String memberLinkedin;

    @ApiModelProperty(value = "成员facebook")
    private String memberFacebook;

    @ApiModelProperty(value = "成员twitter")
    private String memberTwitter;

    @ApiModelProperty(value = "成员其他信息")
    private String memberOtherinfo;


}
