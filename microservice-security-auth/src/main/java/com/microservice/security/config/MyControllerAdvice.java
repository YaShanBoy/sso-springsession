package com.microservice.security.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
//
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
		System.out.println("打印请求uri："+request.getRequestURI());
		log.info(request.toString(), ex);
		HttpStatus status = getStatus(request);
		Map<String,String> responseBody = new HashMap<String,String>();
		if(ex instanceof IllegalArgumentException || ex instanceof NullPointerException || ex instanceof NestedRuntimeException) {
			responseBody.put("msg", "参数非法，请检查后再尝试！");
		}else {
			responseBody.put("msg", "系统繁忙，请稍后再试！");
		}
		return new ResponseEntity<>(responseBody, status);
	}
	
	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
}
