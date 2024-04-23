package com.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ekko
 * @Description: BlockingLoadBalancerClient配置类,创建自定义的BlockingLoadBalancerClient Bean
 * @Date: 2023/9/20 16:31
 */
@Configuration
public class BlockingLoadBalancerClientConfig {

    @Autowired
    LoadBalancerClientFactory loadBalancerClientFactory;

    @Autowired
    LoadBalancerProperties properties;

    @Bean
    public LoadBalancerClient BlockingLoadBalancerClient() {
        return new CustomBlockingLoadBalancerClient(loadBalancerClientFactory, properties);
    }
}
