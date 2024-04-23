# Spring Cloud Gateway ReactiveFeign

## 说明
本项目包含user-feign、spring-cloud-gateway两个子模块。

## 项目启动步骤
1、启动nacos，因为feign调用会依赖注册中心，所以需要启动nacos
2、启动user-feign项目
3、启动spring-cloud-gateway项目

## 测试
用postman发起接口调用：

`GET localhost:6666/api/v1/users/getUserInfo`

成功返回用户信息则说明调用成功

## 其他
其他关注的地方，可以通过断点进行调试。

## 注意点
master分支
spring-boot version 2.4.2 对于同步调用feign会报错，需要添加两个配置，具体在/config的
BlockingLoadBalancerClientConfig 和 CustomBlockingLoadBalancerClient 配置

dev分支
但是在spring-boot version 2.3.12.RELEASE 对于同步调用feign不会报错

项目来源:
https://blog.csdn.net/sslulu520/article/details/130127048