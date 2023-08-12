package com.atguigu.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class FlowLimitController {

     @Resource
     private TestService testService;


    @GetMapping("/testA")
    public String testA() {
        // 测试阈值类型：线程数
        //try {
        //    TimeUnit.MILLISECONDS.sleep(800);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        return "------testA"+testService.myService();

    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "------testB"+testService.myService();

    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "------testD";
    }


    @GetMapping("/testE")
    public String testE() {
        log.info("testE 测试异常数");
        int age = 10 / 0;
        return "------testE 测试异常数";
    }

    @GetMapping("/testHotHey")
    @SentinelResource(value = "testHotHey",blockHandler = "deal_testHotHey")
    public String testHotKey(@RequestParam(value="p1",required=false) String p1,
                             @RequestParam(value="p2",required=false) String p2){

        return "------testHotKey";
    }

    public String deal_testHotHey(String p1, String p2, BlockException exception){
        return "------deal_testHotHey";
    }

}
