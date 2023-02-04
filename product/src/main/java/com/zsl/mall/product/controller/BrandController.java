package com.zsl.mall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.zsl.mall.common.valid.AddGroup;
import com.zsl.mall.common.valid.UpdateGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zsl.mall.product.entity.BrandEntity;
import com.zsl.mall.product.service.BrandService;
import com.zsl.mall.common.utils.PageUtils;
import com.zsl.mall.common.utils.R;

import javax.validation.Valid;


/**
 * 品牌
 *
 * @author zsl
 * @email s1242556348@qq.com
 * @date 2023-02-01 10:04:49
 */
@Slf4j
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;


    @PostMapping("test")
    public R test(@Valid @RequestBody BrandEntity brandEntity){
        log.info("===========test");
        return R.ok();
    }

    @PostMapping("addGroup")
    public R addGroup(@Validated(value = AddGroup.class) @RequestBody BrandEntity brandEntity){
        log.info("===========addGroup");
        return R.ok();
    }

    @PostMapping("updateGroup")
    public R updateGroup(@Validated(value = {UpdateGroup.class}) @RequestBody BrandEntity brandEntity){
        log.info("===========updateGroup");
        return R.ok();
    }



    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@RequestBody BrandEntity brand){
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
