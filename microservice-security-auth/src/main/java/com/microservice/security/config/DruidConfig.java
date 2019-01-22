package com.microservice.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {
	@Bean
	public ServletRegistrationBean<?> statViewServlet() {
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
		//servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		//servletRegistrationBean.addInitParameter("deny", "");
		servletRegistrationBean.addInitParameter("loginUsername", "druid");
		servletRegistrationBean.addInitParameter("loginPassword", "admin123");
		//servletRegistrationBean.addUrlMappings("/*");
		return servletRegistrationBean;
	}
	@Bean
	public FilterRegistrationBean<?> statViewFilter(){
		FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		//*.jpg,*.png,*.gif,*.ico,
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.css,/druid/*");
		return filterRegistrationBean;
	}
}
