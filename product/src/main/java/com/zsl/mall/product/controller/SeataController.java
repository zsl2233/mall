package com.zsl.mall.product.controller;

import com.zsl.mall.common.feign.MemberFeignClient;
import com.zsl.mall.common.utils.R;
import com.zsl.mall.product.service.BrandService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("product/seata")
public class SeataController {
    @Autowired
    private BrandService brandService;

    @Autowired
    private MemberFeignClient memberFeignClient;

    @RequestMapping("/test")
    public R test(){
        log.info("==========================product test");
        brandService.insert("seata", UUID.randomUUID().toString());
        return R.error();
    }
}
