package com.zsl.mall.product.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsl.mall.common.utils.PageUtils;
import com.zsl.mall.common.utils.Query;

import com.zsl.mall.product.dao.BrandDao;
import com.zsl.mall.product.entity.BrandEntity;
import com.zsl.mall.product.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>()
        );

        return new PageUtils(page);
    }

    @Cacheable(value ="brand",key = "#userId",sync = true)
    @Override
    public List<BrandEntity> getList(String userId){
        return this.list();
    }

    @CacheEvict(value = "brand",allEntries = true)
    @Override
    public void insert(String name,String userId) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName(name);
        this.save(brandEntity);
    }

}