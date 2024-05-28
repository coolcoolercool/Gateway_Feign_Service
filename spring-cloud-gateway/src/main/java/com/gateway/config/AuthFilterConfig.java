package com.gateway.config;

import com.gateway.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
public class AuthFilterConfig implements InitializingBean {
    @Autowired
    private UserClient userClient;

    public Map<String, String> innerMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("#########TestReactiveFilter filter userClient:{}", userClient.getFeignUserInfo());

    }
}
