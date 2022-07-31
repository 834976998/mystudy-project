package com.wit.wafon.hutoolstudy.service.impl;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.wit.wafon.hutoolstudy.service.DmDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/7/21 10:33
 * @describe
 */
@Service
@Slf4j
public class DmDbServiceImpl implements DmDbService {
    @Autowired
    private List<Db> db;


    @Override
    public void getAllUser() throws SQLException {
        List<Entity> query = db.get(1).query("select * from TB_USER");
        query.forEach(System.out::println);
    }
}
