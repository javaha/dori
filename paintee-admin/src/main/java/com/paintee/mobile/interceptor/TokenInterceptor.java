/**
@file TokenInterceptor.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | TokenInterceptor.java |    
| Package | com.paintee.mobile.interceptor |    
| Project name | paintee-admin |    
| Type name | TokenInterceptor |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 4:30:04 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
@class TokenInterceptor
com.paintee.mobile.interceptor \n
   ㄴ TokenInterceptor.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 4:30:04 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 해당 url token 의 유효성 확인을 위한 interceptor
*/
public class TokenInterceptor extends HandlerInterceptorAdapter {
	private final static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("postHandle executed");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.info("afterCompletion executed");
	}
}
