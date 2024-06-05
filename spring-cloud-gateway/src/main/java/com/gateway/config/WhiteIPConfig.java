package com.gateway.config;


import com.gateway.feign.UserReactiveClient;
import com.gateway.response.ResultResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Getter
@Setter
@Slf4j
public class WhiteIPConfig implements InitializingBean {
    private Map<String, List<String>> innerMap = new HashMap<>();


    @Autowired
    @Lazy
    private UserReactiveClient userReactiveClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        ResultResponse response = userReactiveClient.getFeignUserInfo().block();
        log.info("WhiteIPConfig response:{}", response);
        log.info("WhiteIPConfig response data:{}", response.getData());
    }
}
