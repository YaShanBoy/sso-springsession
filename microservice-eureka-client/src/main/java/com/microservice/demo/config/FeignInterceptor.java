package com.microservice.demo.config;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FeignInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
		HttpServletRequest request = attributes.getRequest(); 
		Enumeration<String> headerNames = request.getHeaderNames(); 
		if (headerNames != null) { 
			while (headerNames.hasMoreElements()) { 
				String name = headerNames.nextElement(); 
				String values = request.getHeader(name); 
				if("Cookie".equalsIgnoreCase(name)) {
					log.debug("feign中继的cookie：{}",values);
					requestTemplate.header(name, values); 
				}
			}
		}
	}

}
