package com.zsl.mall.product.service.impl;

import com.zsl.mall.common.feign.MemberFeignClient;
import com.zsl.mall.common.utils.R;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private MemberFeignClient memberFeignClient;

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

//    @CacheEvict(value = "brand",allEntries = true)
    @Override
    @GlobalTransactional
    public void insert(String name,String userId) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName(name);
        this.save(brandEntity);
        log.info("Seata全局事务id=================>{}", RootContext.getXID());
        R r = memberFeignClient.test();
        int i =1;
//        int i = 10/0;
    }


}