## 工程模块说明

- candy-parent    Maven父工程
- candy-common	公共类模块
- candy-security    CAS认证客户端 
- cas-server-webapp    CAS认证服务端（Tomcat应用）
- candy-dao-pojo    实体类和数据库访问层
- candy-oss-upload    阿里OSS文件上传模块
- candy-user-interface 用户接口
- candy-user-web   用户木块（SpringBoot项目）
- candy-user-service 用户服务模块（SpringBoot项目dubbo提供者）
- candy-good-interface 商品接口
- candy-good-web    商品模块（SpringBoot项目）
- candy-good-service 商品服务模块（SpringBoot项目dubbo提供者）

​              

## 开始使用

git clone ...	克隆仓库

cd candy

./setup.sh 开始安装

./start.sh 启动服务

## 解决方案

- 远程调用：dubble+zookeeper
- 单点登入：CAS
- AIP网关：nginx反向代理实现API聚合
- AJAX跨域请求：nginx 加响应头
- 搜索：Solr ，分词器：
- 分布式文件系统：阿里云OSS上传
- 分布式数据库：Mycat切片



## 用户中心微服务

> ​	用户中心微服务

### 用户注册

> ​	手机号加密码快速注册，手机号验证码使用阿里大于

 - 邮箱规则 
 - 用户名规则
 - 手机号规则
 - 密码加密: MD5

### 获取用户信息



### CAS单点登入

#### 用户登入

> ​	验证码识别
>
> ​	通过邮箱，手机号或用户名登入
>
> ​	单点登入解决方案SpringSecurity+CAS

#### 管理员登入

## 商品管理

### 用户发布商品

