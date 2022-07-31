package com.wit.wafon.hutoolstudy.configs;

import cn.hutool.db.Db;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengwang26
 * @date 2022/7/4 23:49
 * @describe
 */
@Configuration
public class DblinksConfig {
    @Bean
    public List<Db> getDb(){
        List<Db> r = new ArrayList<>();
        Db group1 = Db.use("group1");
        Db group2 = Db.use("group2");
        r.add(group1);
        r.add(group2);
        return r;
    }
}
