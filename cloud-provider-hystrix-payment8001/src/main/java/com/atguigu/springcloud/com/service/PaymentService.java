package com.atguigu.springcloud.com.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService{

    public String  paymentInfo_ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_ok,id="+id;
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    public String  paymentInfo_timeout(Integer id) {
        int timeNumber = 5;
        //int age = 1/0;
        try {
           TimeUnit.SECONDS.sleep(timeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
       // return "线程池：" + Thread.currentThread().getName() + " paymentInfo_timeout,id=" + id+"超时方法，耗时（单位s）"+timeNumber;
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_timeout,id=" + id+"超时方法，耗时（单位s）";
    }

    public String  paymentInfo_timeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_timeoutHandler,id=" + id+"超时方法，耗时（单位s），哭哭哭！！！";
    }

   //---------服务器熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),/* 是否开启断路器*/
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),// 时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")// 失败率达到多少后跳闸
    })
   public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("id不能为负");
        }

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"调用成功，流水号："+serialNumber;

   }

    /**
     * paymentCircuitBreaker 方法的 fallback，<br/>
     * 当断路器开启时，主逻辑熔断降级，该 fallback 方法就会替换原 paymentCircuitBreaker 方法，处理请求
     *
     * @param id
     * @return
     */
    public String paymentCircuitBreakerFallback(Integer id) {
        return Thread.currentThread().getName() + "\t" + "id 不能负数或超时或自身错误，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }

}
