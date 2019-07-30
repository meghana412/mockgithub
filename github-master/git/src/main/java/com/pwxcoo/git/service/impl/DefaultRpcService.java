package com.pwxcoo.git.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pwxcoo.git.service.RpcService;

/**
 * @author pwxcoo
 * @package com.pwxcoo.git.service.impl
 * @email pwxcoo@gmail.com
 * @time 2018/09/23 15:56
 * @description
 */
@Service(
        version = "${git.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DefaultRpcService implements RpcService {

    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot), Test RPC Successfully!";
    }

}