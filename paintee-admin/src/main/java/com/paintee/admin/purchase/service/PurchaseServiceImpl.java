/**
@file PurchaseServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseServiceImpl.java |    
| Package | com.paintee.mobile.painting.service |    
| Project name | paintee-admin |    
| Type name | PurchaseServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 10:59:36 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.purchase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.Code;
import com.paintee.common.repository.entity.CodeExample;
import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.Purchase;
import com.paintee.common.repository.entity.PurchaseExample;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PaintingVO;
import com.paintee.common.repository.entity.vo.PurchaseSearchVO;
import com.paintee.common.repository.entity.vo.PurchaseVO;
import com.paintee.common.repository.helper.PaintingHelper;
import com.paintee.common.repository.helper.PurchaseHelper;
import com.paintee.common.repository.helper.UserHelper;
import com.paintee.common.repository.mapper.CodeMapper;

/**
@class PurchaseServiceImpl
com.paintee.mobile.painting.service \n
   ㄴ PurchaseServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 10:59:36 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림에 대한 service 구현채
*/
@Service(value="com.paintee.admin.purchase.service.PurchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	private final static Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);
	
	@Autowired
	private PurchaseHelper purchaseHelper;
	
	@Autowired
	private CodeMapper codeMapper;
	
	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private PaintingHelper paintingHelper;
	
	
	@Override
	public Map<String, Object> getPurchaseList(PurchaseSearchVO search) {
		
		List<PurchaseVO> list = purchaseHelper.selectPurchaseList(search);
		logger.debug("list : " + list);
		
		int count = purchaseHelper.selectPurchaseListCount(search);
		logger.debug("전체 개수 : " + count);
		
		// 은행 목록 조회
		CodeExample example = new CodeExample();
		CodeExample.Criteria where = example.createCriteria();
		where.andCodeGroupEqualTo("purchaseStatus");
		List<Code> statusList = codeMapper.selectByExample(example);
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("count", count);
		result.put("statusList", statusList);
		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 구매상태 변경시의 처리할 일 정의
	 @remark
	   1. 구매테이블의 상태를 변경
	   2. 구매상태를 발송으로 변경한 경우 
		  - 구매한 그림의 사용자의 수익 전체 금액(earn_total_money) 증가
	 @see com.paintee.admin.purchase.service.PurchaseService#modPurchaseStatus(com.paintee.common.repository.entity.Purchase)
	*/
	@Override
	public void modPurchaseStatus(Purchase purchase) {
		
		
		String paintingId = purchase.getPaintingId();
		String userId = purchase.getUserId();
		User user = null;
		
		// 2. 구매상태가 발송으로 변경된 경우
		switch (purchase.getPurchaseStatus()) {
		// 발송
		case "2":
			// 회원 테이블 정보 추가 - 수익 전체 금액(earn_total_money)
			PaintingVO pInfo = paintingHelper.selectPaintingInfo(paintingId);
			user = new User();
			// 구매한 그림의 작가에게 수익금액 쌓기
			user.setUserId(pInfo.getArtistId());
			user.setEarnTotalMoney(new Float(0.5));
			
			userHelper.updateUserEarnTotalMoney(user);
			break;
			
		// 환불처리 	
		case "6":  
			// 회원의 그림을 이전에 구매했는지 카운트를 조회
			// 구매상태가 요청-1/발송-2/환불요청-3/재발송요청-4/재발송처리-5/환불처리-6/삭제-7 
			List<String> statusList = new ArrayList<>();
			statusList.add("1");
			statusList.add("2");
			statusList.add("3");
			statusList.add("4");
			statusList.add("5");
			PurchaseExample example = new PurchaseExample();
			example.createCriteria().andUserIdEqualTo    (userId)
			                        .andPaintingIdEqualTo(paintingId)
			                        .andPurchaseStatusIn(statusList);
			int puchaseCount = purchaseHelper.countByExample(example);
			logger.debug("구매카운트 : {}", puchaseCount);
			
			// 그림 테이블 정보 업데이트 - posted_num 무조건 1 감소, 
			// posted_people_cnt (구매 테이블에 해당 사용자가 그림을 1번만 구매한 경우 확인 후 감소 시킴)
			Painting painting = new Painting();
			painting.setPaintingId(paintingId);
			if (puchaseCount == 1) {
				painting.setPostedPeopleCnt(-1);
			} else {
				painting.setPostedPeopleCnt(0);
			}
			painting.setPostedNum(-1);
			paintingHelper.updatePaintingPurchaseInfo(painting);
			
		// 삭제와 환불처리 상태일 경우 공통으로 구매자의 구매카운트를 감소시킨다.
		// 회원 테이블 정보 추가 - 구매카운트(post_cnt) 감소
		case "7":
			user = new User();
			user.setUserId(userId);
			user.setPostCnt(-1);
			userHelper.updateUserInfo(user);
			break;
		}

		// 1. 구매테이블의 상태를 변경
		purchase.setStatusUpdateDate(new Date());
		purchaseHelper.updateByPrimaryKeySelective(purchase);
	}
}
