package com.lee.beans;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

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
 * @since 2023-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Rule对象", description="")
public class Rule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "规则组成")
    private byte[] rule;

    @ApiModelProperty(value = "规则描述")
    private String ruleDes;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty(value = "创建日期")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新日期")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtUpdate;


}
