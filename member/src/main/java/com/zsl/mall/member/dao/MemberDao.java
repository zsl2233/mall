package com.zsl.mall.member.dao;

import com.zsl.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author zsl
 * @email s1242556348@qq.com
 * @date 2023-02-01 13:58:23
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
