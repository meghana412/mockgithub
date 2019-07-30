package com.pwxcoo.github.api.rpc;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pwxcoo.git.service.RpcService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.api
 * @email pwxcoo@gmail.com
 * @time 2018/09/23 16:02
 * @description
 */
@RestController
public class RpcConsumerController {

    @Reference(version = "${git.service.version}",
            application = "${dubbo.application.id}",
            registry = "${dubbo.registry.id}")
    private RpcService rpcService;

    @RequestMapping("/test/rpc")
    public String sayHello(@RequestParam String name) {
        return rpcService.sayHello(name);
    }

}