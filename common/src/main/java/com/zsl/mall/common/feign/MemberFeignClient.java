package com.zsl.mall.common.feign;

import com.zsl.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "member")
public interface MemberFeignClient {
    @RequestMapping(value = "member/seata/test")
    R test();
}
