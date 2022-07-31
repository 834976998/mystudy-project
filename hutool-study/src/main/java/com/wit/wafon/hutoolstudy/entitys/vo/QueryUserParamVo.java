package com.wit.wafon.hutoolstudy.entitys.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author fengwang26
 * @date 2022/7/4 21:20
 * @describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@ApiModel(value = "QueryUserParamVo",description = "用户查询接口")
public class QueryUserParamVo implements Serializable {
    @ApiModelProperty("用户ID")
    private int id;
    @ApiModelProperty("用户名")
    private String name;
}
