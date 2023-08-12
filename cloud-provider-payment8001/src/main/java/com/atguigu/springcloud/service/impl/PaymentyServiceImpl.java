package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentyServiceImpl implements PaymentyService {
    @Resource
    private PaymentDao paymentDao;

    public  int create(Payment paymenty){
        return paymentDao.create(paymenty);
    };

    public Payment getPaymentById(@Param("id") Long id){
        return paymentDao.getPaymentById(id);
    };
}
