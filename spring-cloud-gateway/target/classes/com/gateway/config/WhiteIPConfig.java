package com.gateway.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Getter
@Setter
public class WhiteIPConfig {
    private Map<String, List<String>> innerMap = new HashMap<>();
}
