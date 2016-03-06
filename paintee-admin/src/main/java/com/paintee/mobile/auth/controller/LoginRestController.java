/**
@file LoginRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | LoginRestController.java |    
| Package | com.paintee.mobile.auth.controller |    
| Project name | paintee-admin |    
| Type name | LoginRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 1:51:11 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.auth.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.vo.UserLoginVO;
import com.paintee.mobile.auth.service.LoginService;

/**
@class LoginRestController
com.paintee.mobile.auth.controller \n
   ㄴ LoginRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 1:51:11 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 인증관련 rest controller
*/
@RestController(value="com.paintee.mobile.auth.controller.LoginRestController")
public class LoginRestController {
	private final static Logger logger = LoggerFactory.getLogger(LoginRestController.class);

	@Value("#{config['common.login.hash.expireDay'] }")
	private int expireDay;

	@Autowired
	private LoginService loginService;
	
	 /**
	 @fn login
	 @brief 함수 간략한 설명 : 사용자 인증
	 @remark
	 - 함수의 상세 설명 : userId 와 passwod 를 사용하여 사용자 인증을 한다.
	 @param userLoginVO
	 @return 
	*/
	@RequestMapping(value = "/api/login", method = {RequestMethod.POST})
	public Map<String, Object> login(UserLoginVO userLoginVO, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();

		if(userLoginVO.getEmail() == null || userLoginVO.getEmail().trim().length() == 0) {
			resultMap.put("errorNo", 401);
		} else if(userLoginVO.getPassword() == null || userLoginVO.getPassword().trim().length() == 0) {
			resultMap.put("errorNo", 402);
		} else {
			resultMap = loginService.login(userLoginVO);

			int resultCode = Integer.parseInt(String.valueOf(resultMap.get("errorNo")).trim());
			if(resultCode == 0) {
				int cookieMaxAge = expireDay*24*60*60;
				String domain = "localhost";

				//사용자 id cookie 생성
				Cookie userIdCookie = new Cookie("userId", String.valueOf(resultMap.get("userId")));
				//hash 의 생존 주기와 같게 설정
				userIdCookie.setMaxAge(cookieMaxAge);
				userIdCookie.setDomain(domain);
				userIdCookie.setPath("/");
				response.addCookie(userIdCookie);

				//사용자 eamil cookie 생성
				Cookie emailCookie = new Cookie("email", String.valueOf(resultMap.get("email")));
				//hash 의 생존 주기와 같게 설정
				emailCookie.setMaxAge(cookieMaxAge);
				emailCookie.setDomain(domain);
				emailCookie.setPath("/");
				response.addCookie(emailCookie);

				//사용자 hash cookie 생성
				Cookie hashCookie = new Cookie("hash", String.valueOf(resultMap.get("hash")));
				//hash 의 생존 주기와 같게 설정
				hashCookie.setMaxAge(cookieMaxAge);
				hashCookie.setDomain(domain);
				hashCookie.setPath("/");
				response.addCookie(hashCookie);

				try {
					//사용자 name cookie 생성
					String name = String.valueOf(resultMap.get("name"));
					Cookie nameCookie = null;
					nameCookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
					//hash 의 생존 주기와 같게 설정
					nameCookie.setMaxAge(cookieMaxAge);
					nameCookie.setDomain(domain);
					nameCookie.setPath("/");
					response.addCookie(nameCookie);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return resultMap;
	}
}
