/**
@file FollowRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FollowRestController.java |    
| Package | com.paintee.mobile.follow.controller |    
| Project name | paintee-admin |    
| Type name | FollowRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:23:44 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.follow.controller;

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

import com.paintee.common.repository.entity.vo.FollowSearchVO;
import com.paintee.common.repository.entity.vo.FollowVO;
import com.paintee.mobile.follow.service.FollowService;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class FollowRestController
com.paintee.mobile.follow.controller \n
   ㄴ FollowRestController.java
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
@RestController(value="com.paintee.mobile.follow.controller.FollowRestController")
public class FollowRestController {
	private final static Logger logger = LoggerFactory.getLogger(FollowRestController.class);

	@Autowired
	private FollowService followService;

	@RequestMapping(value="/api/index/follow/count", method=RequestMethod.GET)
	public Map<String, Object> followCount(LoginedUserVO loginedUserVO) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("loginedUserVO:{}", loginedUserVO);
		int errorNo = 0;
		String errorMsg = "";
		
		// 로그인 사용자 아이디
		String userId = loginedUserVO.getUserId();
		
		// 데이터 조건 설정
		FollowSearchVO search = new FollowSearchVO();
		
		// 로그인 사용자 아이디
		search.setUserId(userId);
		
		FollowVO follow = followService.getFollowCount(search);
		resultMap.put("follow", follow);
		resultMap.put("errorNo", errorNo);
		resultMap.put("errorMsg", errorMsg);

		return resultMap;
	}
	
	@RequestMapping(value="/api/index/follow/list", method=RequestMethod.GET)
	public Map<String, Object> paintingList(LoginedUserVO loginedUserVO, 
			@RequestParam(name="startRow", required=false, defaultValue="0") Integer startRow) 
					throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();

		logger.debug("loginedUserVO:{}", loginedUserVO);
		logger.debug("startRow:{}", startRow);
		
		int errorNo = 0;
		String errorMsg = "";
		
		// 로그인 사용자 아이디
		String userId = loginedUserVO.getUserId();
		
		// 데이터 조건 설정
		FollowSearchVO search = new FollowSearchVO();
		
		// 요청 데이터 페이징 정보
		search.setStartRow(startRow);
		search.setRowPerPage(5);
		
		// 요청, 발송 상태
		List<String> purchaseStatusList = new ArrayList<>();
		purchaseStatusList.add("C");
		purchaseStatusList.add("S");
		search.setPurchaseStatusList(purchaseStatusList);
		
		// 공개인것만 
		search.setPrivateAt("N");
		
		// 로그인 사용자 아이디
		search.setUserId(userId);
		
		resultMap = followService.getFollowPaintingInfo(search);
		
//		errorNo = followService.follow(followId, followingId);

		resultMap.put("errorNo", errorNo);
		resultMap.put("errorMsg", errorMsg);

		return resultMap;
	}
	
}
