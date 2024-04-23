package com.gateway.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Getter
@Setter
public class WhiteIPConfig implements InitializingBean {
    private Map<String, List<String>> innerMap = new HashMap<>();


    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO: 通过feign接口调用
    }
}
