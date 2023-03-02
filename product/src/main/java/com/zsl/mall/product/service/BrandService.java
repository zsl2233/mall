package com.zsl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsl.mall.common.utils.PageUtils;
import com.zsl.mall.product.entity.BrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌
 *
 * @author zsl
 * @email s1242556348@qq.com
 * @date 2023-02-01 10:04:49
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<BrandEntity> getList(String userId);

    void insert(String name,String userId);
}

