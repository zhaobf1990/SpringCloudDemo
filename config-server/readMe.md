### 通过向Config-Server发送GET请求以直接的方式获取配置参数,可以用下面的方式

#### 不带{label}分支信息,默认访问master分支,可使用:

*  /{application}/{profile}.yml
*  /{application}/{profile}.properties

#### 带{label}分支信息,可使用: 

*  /{label}/{application}/{profile}.yml
*  /{label}/{application}/{profile}.properties
*  /{application}/{profile}[/{label}]
 

