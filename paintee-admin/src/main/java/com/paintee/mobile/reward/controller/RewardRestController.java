/**
@file PurchaseController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseController.java |    
| Package | com.paintee.mobile.purchase.controller |    
| Project name | paintee-admin |    
| Type name | PurchaseController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 1:33:20 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.reward.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.Reward;
import com.paintee.common.repository.entity.User;
import com.paintee.mobile.reward.service.RewardService;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class PurchaseController
com.paintee.mobile.purchase.controller \n
   ㄴ PurchaseController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 1:33:20 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@RestController(value="com.paintee.mobile.reward.controller.RewardRestController")
public class RewardRestController {
	
	private final static Logger logger = LoggerFactory.getLogger(RewardRestController.class);
	
	@Autowired
	private RewardService rewardService;
	
	@RequestMapping(value="/api/reward/info", method={RequestMethod.GET})
	public Map<String, Object> info(LoginedUserVO loginedUserVO) throws Exception {
		// 구매관련 정보 등록
		User user = new User();
		user.setUserId(loginedUserVO.getUserId());
		
		Map<String, Object> result = rewardService.rewardInfo(user);
		return result;
	}
	
	@RequestMapping(value="/api/reward", method={RequestMethod.POST})
	public Map<String, Object> addReward(LoginedUserVO loginedUserVO, @RequestBody Reward reward) throws Exception {
		
		// 리워드 정보 등록
		reward.setUserId(loginedUserVO.getUserId());
		rewardService.addReward(reward);
		
		Map<String, Object> result = new HashMap<>();
		// 에러정보
		result.put("errorNo", 0);
		result.put("errorMsg", "");
		return result;
	}
}













