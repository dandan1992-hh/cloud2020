package com.atguigu.springcloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @SentinelResource(value = "myService",blockHandler="blockHandlerTest" )
    public String myService() {
        return "test service";
    }

    public String blockHandlerTest(BlockException blockException){
        return "被限流了！！！！";
    }

}
