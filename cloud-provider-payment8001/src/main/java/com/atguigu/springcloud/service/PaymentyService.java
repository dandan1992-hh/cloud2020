package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;


public interface PaymentyService

{
    public  int create(Payment paymenty);

    public Payment getPaymentById(@PathVariable("id") Long id);
}

