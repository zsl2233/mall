package com.zsl.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsl.mall.common.utils.PageUtils;
import com.zsl.mall.coupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author zsl
 * @email s1242556348@qq.com
 * @date 2023-02-01 11:43:04
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

