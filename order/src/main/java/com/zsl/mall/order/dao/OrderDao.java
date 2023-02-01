package com.zsl.mall.order.dao;

import com.zsl.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author zsl
 * @email s1242556348@qq.com
 * @date 2023-02-01 13:56:09
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
