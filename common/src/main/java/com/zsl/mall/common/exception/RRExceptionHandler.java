/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.zsl.mall.common.exception;
import com.zsl.mall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
@RestControllerAdvice
public class RRExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return R.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

//	@ExceptionHandler(AuthorizationException.class)
//	public R handleAuthorizationException(AuthorizationException e){
//		log.error(e.getMessage(), e);
//		return R.error("没有权限，请联系管理员授权");
//	}

	/**
	 * bean参数验证 带requestbody的方式.
	 *
	 * @param e RuntimeException
	 * @return String
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public R validExceptionHandler(MethodArgumentNotValidException e) {
		log.error("数据校验错误：{}，数据类型：{}",e.getMessage(),e.getFieldError());
		Map<String,String> map = new HashMap<>();
		BindingResult bindingResult = e.getBindingResult();
		bindingResult.getFieldErrors().forEach(fieldError->
			map.put(fieldError.getField(),fieldError.getDefaultMessage())
		);
		return R.error(ResponseCodeEnum.VALID_EXCEPTION.getCode(), ResponseCodeEnum.VALID_EXCEPTION.getMsg()).data(map);
	}



	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		log.info("==========================error");
		log.error(e.getMessage(), e);
		return R.error();
	}
}
