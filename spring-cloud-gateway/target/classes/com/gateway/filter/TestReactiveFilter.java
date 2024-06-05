package com.gateway.filter;

import com.alibaba.fastjson.JSON;
//import com.gateway.feign.UserClient;
import com.gateway.config.WhiteIPConfig;
import com.gateway.feign.UserReactiveClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


@Component
@Slf4j
public class TestReactiveFilter implements GlobalFilter, Ordered , InitializingBean {

    @Autowired
    @Lazy
    private WhiteIPConfig whiteIPConfig;

    @Autowired
    @Lazy
    private UserReactiveClient userReactiveClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("@@@@@@@@TestReactiveFilter filter:{}", userReactiveClient.getFeignUserInfo());
        //log.info("#########TestReactiveFilter filter:{}", userClient.getFeignUserInfo());
//        return chain.filter(exchange);
        if (whiteIPConfig.getInnerMap().isEmpty()) {
            return userReactiveClient.getFeignUserInfo().flatMap(commonResponse -> {
                if (!commonResponse.isSuccess()) {
                    return Mono.defer(() -> {
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        final ServerHttpResponse response = exchange.getResponse();
                        byte[] bytes = JSON.toJSONString(commonResponse).getBytes(StandardCharsets.UTF_8);
                        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                        return response.writeWith(Flux.just(buffer));
                    });
                } else {
                    log.info("@@@@@@@@@@User-Info: [{}]", JSON.toJSONString(commonResponse.getData()));
                    return chain.filter(exchange);
                }
            });
        } else {
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
       /* if (whiteIPConfig.getInnerMap().isEmpty()) {
            return userReactiveClient.getFeignUserInfo().flatMap(commonResponse -> {
                if (!commonResponse.isSuccess()) {

                } else {
                    log.info("User-Info: [{}]", JSON.toJSONString(commonResponse.getData()));

                }
            });
        }*/
    }
}
