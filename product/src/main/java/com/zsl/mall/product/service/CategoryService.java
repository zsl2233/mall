package com.zsl.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsl.mall.common.utils.PageUtils;
import com.zsl.mall.product.entity.CategoryEntity;

import java.util.Map;

/**
 * 商品三级分类
 *
 * @author zsl
 * @email s1242556348@qq.com
 * @date 2023-02-01 10:04:49
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

