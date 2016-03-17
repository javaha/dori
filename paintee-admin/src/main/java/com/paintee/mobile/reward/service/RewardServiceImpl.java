/**
@file PurchaseServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseServiceImpl.java |    
| Package | com.paintee.mobile.purchase.service |    
| Project name | paintee-admin |    
| Type name | PurchaseServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 1:54:10 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.reward.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.Code;
import com.paintee.common.repository.entity.CodeExample;
import com.paintee.common.repository.entity.Reward;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.UserExample;
import com.paintee.common.repository.entity.vo.RewardVO;
import com.paintee.common.repository.helper.RewardHelper;
import com.paintee.common.repository.mapper.CodeMapper;
import com.paintee.common.repository.mapper.UserMapper;

/**
@class PurchaseServiceImpl
com.paintee.mobile.purchase.service \n
   ㄴ PurchaseServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 1:54:10 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@Service(value="com.paintee.mobile.reward.service.RewardServiceImpl")
public class RewardServiceImpl implements RewardService {
	private final static Logger logger = LoggerFactory.getLogger(RewardServiceImpl.class);
	
	@Autowired
	private RewardHelper rewardHelper;
	
	@Autowired
	private CodeMapper codeMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public Map<String, Object> rewardInfo(User user) {
		
		// 기본 정보 조회
		RewardVO reward = rewardHelper.selectRewardInfo(user);
		
		// 은행 목록 조회
		CodeExample example = new CodeExample();
		CodeExample.Criteria where = example.createCriteria();
		where.andCodeGroupEqualTo("bank");
		List<Code> banks = codeMapper.selectByExample(example);
		
		Map<String, Object> result = new HashMap<>();
		result.put("reward", reward);
		result.put("banks", banks);
		return result;
	}

	@Override
	public void addReward(Reward reward) {
		// 리워드 정보 등록
		rewardHelper.insertSelective(reward);
		
		// 사용자 테이블 리워드 관련 정보 업데이트
		User user = new User();
		// 요청한 리워드 금액 + 수수료
		user.setEarnRewordMoney(reward.getEarmRequestedMoney() + 5.0f);
		
		// 사용자 테이블 업데이트 조건 설정
		UserExample example = new UserExample();
		UserExample.Criteria where = example.createCriteria();
		where.andUserIdEqualTo(reward.getUserId());
		
		// 사용자 테이블 업데이트
		userMapper.updateByExampleSelective(user, example);
	}
}






