package com.zsl.mall.member.controller;

import com.zsl.mall.common.utils.R;
import com.zsl.mall.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("member/seata")
public class SeataController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/test")
    public R test() throws Exception {
        log.info("==========================member test");
        memberService.insert("seata", UUID.randomUUID().toString());
        try {
            int i = 10/0;
        }catch (Exception e){

        }
        return R.ok();
    }
}
