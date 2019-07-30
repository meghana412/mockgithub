## Todo

- **repository_subscription in home**
- use csrf when register & login
- use ajax when register & login
- log out
- username and repository name need constraint
- spring boot 热部署, CI & CD
- ssh 密钥管理
- JWT in restful authentication
- recommend in home
- ~~使用 lombok~~
- ~~universal error handler~~

## Log

- 20181007:
    - complete code presentation module
- 20181006:
    - user profile ui
    - discover & user_subscription in home
- 20181005:
    - new repository module
    - follow function 
- 20181004：
    - code highlight and line number
    - 异常处理模块
- 20181003:
    - 添加 DAO
    - 用户主页编写
- 20181002:
    - random avatar
- 20181001:
    - 登录注册，redis 存储 session
    - thymeleaf 前端模板 engine
- 20180925: 
    - 打通 git RPC 调用流程。（dubbo + zookeeper）
    - 打通服务器部署流程。（Ali Cloud）

## Design

- Storage 
    - repositories
        - hard disk (=> NFS filesystem)
    - user, repositories metadata...
        - mysql
        - redis
- Web Service (Spring boot)
    - Subscription (Mail + Feed)
    - Online Repositories
- Git Service
    - RPC
    - SSH
