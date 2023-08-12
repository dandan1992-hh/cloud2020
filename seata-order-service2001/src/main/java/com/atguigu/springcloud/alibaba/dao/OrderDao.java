package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
   // 1.新建订单
   public void create(Order order);

   // 2.修改订单状态，从0改为1
   public  void update(@Param("userId") Long userId,@Param("status") Integer status);
}
