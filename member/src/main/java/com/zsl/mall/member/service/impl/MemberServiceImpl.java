package com.zsl.mall.member.service.impl;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsl.mall.common.utils.PageUtils;
import com.zsl.mall.common.utils.Query;

import com.zsl.mall.member.dao.MemberDao;
import com.zsl.mall.member.entity.MemberEntity;
import com.zsl.mall.member.service.MemberService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void insert(String username, String userId) throws Exception {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUsername(username);
        this.save(memberEntity);
        log.info("Seata全局事务id=================>{}", RootContext.getXID());
    }

}