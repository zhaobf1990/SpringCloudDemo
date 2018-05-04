功能:通过注册中心访问hello-server服务,并实现客户端负载均衡

1.增加eureka和ribbon包的依赖

2.通过注解开始自动配置(自动发现注册中心,并将自己注册为服务).

3.配置服务中心地址.

4.通过restTemplate方法hello-service.







发现的问题:

1.HelloService服务原本有两台,如果其中一台关闭,则ribbon-consumer在一分钟内,有1/2的概念是请求出错的.因为请求被分发到已关闭的实例