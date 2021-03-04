/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package pers.hao.scts.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pers.hao.scts.common.exception.ResultException;
import pers.hao.scts.common.util.Result;

/**
 * 异常处理器
 *
 * @author Mark sunlightcs@gmail.com
 */

@Slf4j
@RestControllerAdvice()
public class ResponseResultBodyAdvice {

	@ExceptionHandler(Exception.class)
	public Result<?> exceptionHandler(Exception ex) {
		log.error("ExceptionHandler: {}", ex.getMessage());
		if (ex instanceof ResultException) {
			ResultException resultException = (ResultException) ex;
			return Result.failure(resultException.getResultStatus());
		} else {
			return Result.failure();
		}
	}

}
