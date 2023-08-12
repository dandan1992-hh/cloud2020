package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentyService

{
    public  int create(Payment paymenty);

    public Payment getPaymentById(@Param("id") Long id);
}

