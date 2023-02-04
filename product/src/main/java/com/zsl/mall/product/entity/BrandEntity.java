package com.zsl.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;


import com.zsl.mall.common.valid.AddGroup;
import com.zsl.mall.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 品牌
 * 
 * @author zsl
 * @email s1242556348@qq.com
 * @date 2023-02-01 10:04:49
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@Null(groups = {AddGroup.class},message = "新增时id必须为空")
	@NotNull(groups = {UpdateGroup.class},message = "更新时id不能为空")
	private Long brandId;
	/**
	 * 品牌名
	 */
	private String name;
	/**
	 * 品牌logo地址
	 */
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	private String firstLetter;
	/**
	 * 排序
	 */
	private Integer sort;

}
