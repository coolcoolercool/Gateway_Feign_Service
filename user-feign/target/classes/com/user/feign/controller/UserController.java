package com.user.feign.controller;

import com.user.feign.domain.UserDTO;
import com.user.feign.response.ResultResponse;
import com.user.feign.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("rawtypes")
@RestController
public class UserController {

    @GetMapping("/feign/api/v1/users/checkPermission")
    public ResultResponse checkPermission(@RequestParam String url, @RequestParam String httpMethod) {
        // 权限校验逻辑放在这里
        // checkPermission(url, httpMethod)
        // 检验通过，获取用户信息
        UserDTO userDTO = UserDTO.builder()
                .userMail("123456@qq.com")
                .username("test")
                .userRole("admin")
                .build();
        return ResponseUtil.build(userDTO);
    }

    @GetMapping("/feign/api/v1/users/getUserInfo")
    public ResultResponse getFeignUserInfo() {
        // 上述的httpServletRequest里可以获取到User-Info信息，说明成功从网关带过来了用户信息
        // 这里校验用户的权限，检验通过，获取用户信息
        UserDTO userDTO = UserDTO.builder()
                .userMail("123456@qq.com")
                .username("test")
                .userRole("admin")
                .build();
        return ResponseUtil.build(userDTO);
    }


    @GetMapping("/api/v1/users/getUserInfo")
    public ResultResponse getUserInfo(HttpServletRequest httpServletRequest) {
        // 上述的httpServletRequest里可以获取到User-Info信息，说明成功从网关带过来了用户信息
        // 这里校验用户的权限，检验通过，获取用户信息
        UserDTO userDTO = UserDTO.builder()
                .userMail("123456@qq.com")
                .username("test")
                .userRole("admin")
                .build();
        return ResponseUtil.build(userDTO);
    }

}
