package com.wit.wafon.elasticsearchstudy.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengwang26
 * @date 2022/8/4 23:48
 * @describe
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "snat-es-config")
public class SnatConfig {

    private String indexes;

    private String esIp;

    private int esPort;

    private String userName;

    private String passWord;
}
