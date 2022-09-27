package com.wit.wafon.elasticsearchstudy.configs;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengwang26
 * @date 2022/8/5 0:10
 * @describe
 */
@Slf4j
@Configuration
public class EsClient {

    @Bean("esClient")
    public RestHighLevelClient getClient() {
        RestHighLevelClient client = null;
        SnatConfig snatConfig = new SnatConfig();
        String userName = snatConfig.getUserName();
        String userPwd = snatConfig.getPassWord();
        String ip = snatConfig.getEsIp();
        int port = snatConfig.getEsPort();
        try {
            /** 用户认证对象 */
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            /** 设置账号密码 */
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(userName, userPwd));
            /** 创建rest client对象 */
            RestClientBuilder builder = RestClient.builder(new HttpHost(ip, port))
                    .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(
                                HttpAsyncClientBuilder httpClientBuilder) {
                            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        }
                    });
            client = new RestHighLevelClient(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
}
