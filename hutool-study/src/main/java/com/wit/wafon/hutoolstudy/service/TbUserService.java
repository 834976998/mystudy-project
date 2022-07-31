package com.wit.wafon.hutoolstudy.service;

import com.wit.wafon.hutoolstudy.entitys.vo.QueryUserParamVo;

import java.sql.SQLException;

/**
 * @author fengwang26
 * @date 2022/7/4 21:17
 * @describe
 */
public interface TbUserService {

    public void getUser(QueryUserParamVo queryUserParamVo) throws SQLException;

    void getBySql();

    public Long insertForGeneratedKey(String name,String pwd) throws SQLException;

    public int deleteByName(String name) throws SQLException;
}
