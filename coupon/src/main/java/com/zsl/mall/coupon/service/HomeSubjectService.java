package com.zsl.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsl.mall.common.utils.PageUtils;
import com.zsl.mall.coupon.entity.HomeSubjectEntity;

import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author zsl
 * @email s1242556348@qq.com
 * @date 2023-02-01 11:43:03
 */
public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

