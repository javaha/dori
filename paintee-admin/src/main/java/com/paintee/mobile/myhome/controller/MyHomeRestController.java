/**
@file MyHomeRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | MyHomeRestController.java |    
| Package | com.paintee.mobile.follow.controller |    
| Project name | paintee-admin |    
| Type name | MyHomeRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:23:44 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.myhome.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.vo.MyHomeSearchVO;
import com.paintee.mobile.myhome.service.MyHomeService;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class MyHomeRestController
com.paintee.mobile.follow.controller \n
   ㄴ MyHomeRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:23:44 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - follow rest controller
*/
@RestController(value="com.paintee.mobile.follow.controller.MyHomeRestController")
public class MyHomeRestController {
	private final static Logger logger = LoggerFactory.getLogger(MyHomeRestController.class);

	@Autowired
	private MyHomeService myhomeService;

	@RequestMapping(value="/api/index/myhome/info", method=RequestMethod.GET)
	public Map<String, Object> myhomeInfo(LoginedUserVO loginedUserVO, MyHomeSearchVO search) 
					throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("loginedUserVO:{}", loginedUserVO);
		logger.debug("search:{}", search);

		int errorNo = 0;
		String errorMsg = "";
		
		// 로그인 사용자 아이디
		String userId = loginedUserVO.getUserId();
		
		// 요청 데이터 페이징 정보
		search.setRowPerPage(5);
		
		// 요청, 발송, 재발송요청, 재발송처리 상태
		List<String> purchaseStatusList = new ArrayList<>();
		purchaseStatusList.add("1");  // 요청
		purchaseStatusList.add("2");  // 발송
		purchaseStatusList.add("4");  // 재발송요청
		purchaseStatusList.add("5");  // 재발송처리

		search.setPurchaseStatusList(purchaseStatusList);
		
		// 로그인 사용자 아이디
		search.setUserId(userId);
		
		// 목록에서 버튼 토글시 처리위해 항목 분리
		search.setArtistId(userId);
		resultMap = myhomeService.getMyHomePaintingInfo(search);
		
		// 에러정보
		resultMap.put("errorNo", errorNo);
		resultMap.put("errorMsg", errorMsg);
		
		return resultMap;
	}
	

}
