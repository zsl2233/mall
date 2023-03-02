package com.zsl.mall.product.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zsl.mall.common.valid.AddGroup;
import com.zsl.mall.common.valid.UpdateGroup;
import com.zsl.mall.product.vo.BrandVo;
import jakarta.json.Json;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zsl.mall.product.entity.BrandEntity;
import com.zsl.mall.product.service.BrandService;
import com.zsl.mall.common.utils.PageUtils;
import com.zsl.mall.common.utils.R;

import javax.annotation.Resource;
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

    @Resource
    private RedisTemplate<Object,String> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("test")
    public R test(){
        new BrandEntity();
        log.info("===========test");
        return R.ok();
    }

    @PostMapping("test1")
    public R test1(@Valid @RequestBody BrandEntity brandEntity){
        log.info("===========test");
        return R.ok();
    }

    @RequestMapping("/cache-query")
    public R cacheQuery(@RequestBody Map<String,String> map){
        List<BrandEntity> brandEntityList =  brandService.getList(map.get("userId"));
        return R.ok().put("data",brandEntityList);
    }

    @RequestMapping("/cache-insert")
    public R cacheInsert(@RequestBody Map<String,String> map){
        brandService.insert(map.get("name"),map.get("userId"));
        return R.ok();
    }

    @RequestMapping("redisson-test")
    public R redissonTest() throws InterruptedException {
        RLock rLock = redissonClient.getLock("lock");
        rLock.lock();
        Thread.sleep(10000);
        rLock.unlock();
        return R.ok();
    }


    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(){
        String brandVoListStr = redisTemplate.opsForValue().get("brandVoList");
        if(StringUtils.isBlank(brandVoListStr)){
            //同步代码块，防止缓存穿透
            synchronized (brandService){
                brandVoListStr = redisTemplate.opsForValue().get("brandVoList");
                if(StringUtils.isBlank(brandVoListStr)){
                    List<BrandEntity> brandEntityList = brandService.list();
                    List<BrandVo> brandVoList = brandEntityList.stream().map(brandEntity -> {
                        BrandVo brandVo = new BrandVo();
                        BeanUtils.copyProperties(brandEntity,brandVo);
                        return brandVo;
                    }).collect(Collectors.toList());
                    brandVoListStr = JSONObject.toJSONString(brandVoList);
                    //增加过期时间，防止缓存雪崩
                    redisTemplate.opsForValue().set("brandVoList",brandVoListStr,24l, TimeUnit.HOURS);
                    //为空时增加null值存入，防止缓存穿透
                    if(StringUtils.isBlank(brandVoListStr)){
                        redisTemplate.opsForValue().set("brandVoList","null",24l, TimeUnit.HOURS);
                    }
                }
            }
        }
        //List<BrandVo> brandVoList = JSONObject.parseObject(brandVoListStr,new TypeReference<List<BrandVo>>(){});
        List<BrandVo> brandVoList = JSONObject.parseArray(brandVoListStr).toJavaList(BrandVo.class);
        return R.ok().put("list", brandVoList);
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
    @RequestMapping("/page")
    //@RequiresPermissions("product:brand:list")
    public R page(@RequestParam Map<String, Object> params){
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
