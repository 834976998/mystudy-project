package com.wit.wafon.hutoolstudy.entitys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author fengwang26
 * @date 2022/7/4 21:18
 * @describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class UserDto {

    private int id;
    private String name;
    private String pwd;

}
