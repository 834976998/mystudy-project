package com.wit.wafon.hutoolstudy.service.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.wit.wafon.hutoolstudy.entitys.vo.QueryUserParamVo;
import com.wit.wafon.hutoolstudy.service.TbUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fengwang26
 * @date 2022/7/4 21:17
 * @describe
 */
@Service
@Slf4j
public class TbUserServiceImpl implements TbUserService {

    private final static String BLOG = "blog";
    private final static String TB_USER = "tb_user";

    @Autowired
    private List<Db> db;

    @Override
    public void getUser(QueryUserParamVo queryUserParamVo) throws SQLException {
        int id = queryUserParamVo.getId();
        String name = queryUserParamVo.getName();
        Entity entity = Entity.create(TB_USER).setFieldNames(setField()).set("id", new String[]{"1","2"});
        Entity entity2 = Entity.create(TB_USER).setFieldNames(setField()).set("pwd", "> 1");
        Entity entity3 = Entity.create(TB_USER).set("name", "like %j%");
        List<Entity> result = db.get(0).findAll(entity);
        List<Entity> result2 = db.get(0).findAll(entity2);
        List<Entity> result3 = db.get(0).findAll(entity3);
        result.stream().forEach(System.out::println);
        System.out.println("-----------");
        result2.stream().forEach(System.out::println);
        System.out.println("-----------");
        result3.stream().forEach(System.out::println);

    }

    @Override
    public void getBySql() {
        String sql = "select max(views) rq from "+BLOG;
        try {
            List<Entity> query = db.get(0).query(sql);
            query.stream().forEach(System.out::println);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Long insertForGeneratedKey(String name,String pwd) throws SQLException {
        log.info("插入用户：{}，{}",name,pwd);
        return db.get(0).insertForGeneratedKey(
                Entity.create(TB_USER).set("name", name).set("pwd", pwd)
        );
    }

    @Override
    public int deleteByName(String name) throws SQLException {
        log.info("删除姓名为[{}]的用户。",name);
        return db.get(0).del(
                Entity.create(TB_USER).set("name", name)//where条件
        );
    }

    private Set<String> setField(){
        Set<String> set = new HashSet<>();
        set.add("name");
        return set;
    }


}
