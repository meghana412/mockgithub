# Github

A mock github.

## Architecture

Three modules in this project.

```
github
|
|-git
|-web
|-mailer
```

### git

This module is for storage. All remote repositories for users will be managed by this module, **implementing git operation using [JGit](https://www.eclipse.org/jgit/), invoked by RPC(dubbo + zookeeper), developed with Spring Boot.** (Can be optimized with NFS filesystem, stand-long mode currently)

### web

This module is online github website. **implementing code presentation, github community function, management for users' own repositories, information, ssh key and more..., developed with Spring Boot.** 

### mailer

This module is for **implementing function about messsage subscription and notification**. (Kafka)

## Screenshot

![user.png](https://i.loli.net/2018/10/08/5bbaeaca71592.png)
![home.png](https://i.loli.net/2018/10/08/5bbaeaca75d58.png)
![code.png](https://i.loli.net/2018/10/08/5bbaeacab93ac.png)

[More screenshot...](./screenshot/README.md)

## References

Many open-source documents and software inspired me.

- [GitLab Architecture Overview](https://docs.gitlab.com/ce/development/architecture.html)
- [gabrie-allaigre/avatar-generator](https://github.com/gabrie-allaigre/avatar-generator): avatar generator
- [JGit](https://www.eclipse.org/jgit/): Java interface for git
- [centic9/jgit-cookbook](https://github.com/centic9/jgit-cookbook): A collection of awesome JGit code snippets
- [apache/incubator-dubbo](https://github.com/apache/incubator-dubbo)
- [highlightjs](https://highlightjs.org/)
