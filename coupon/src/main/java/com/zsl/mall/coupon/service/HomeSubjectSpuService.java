package com.zsl.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsl.mall.common.utils.PageUtils;
import com.zsl.mall.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * δΈι’εε
 *
 * @author zsl
 * @email s1242556348@qq.com
 * @date 2023-02-01 11:43:04
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

