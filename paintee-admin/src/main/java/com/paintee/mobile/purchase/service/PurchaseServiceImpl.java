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
package com.paintee.mobile.purchase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.Purchase;
import com.paintee.common.repository.entity.PurchaseExample;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.helper.PaintingHelper;
import com.paintee.common.repository.helper.PurchaseHelper;
import com.paintee.common.repository.helper.UserHelper;

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
@Service(value="com.paintee.mobile.purchase.service.PurchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	private final static Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);
	
	@Autowired
	private PurchaseHelper purchaseHelper;
	
	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private PaintingHelper paintingHelper;
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 그림 구매시 해야할 일
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	   1. 구매테이블에 데이터를 입력한다.
	   2. 회원 테이블 정보 업데이트 
	      - 구매한 그림의 사용자의 수익 전체 금액(earn_total_money) 증가
	      - 로그인한 사용자의 구매카운트(post_cnt) 증가
	   3. 그림 테이블 정보 업데이트 
	      - posted_num 무조건 1 증가, 
	      - posted_people_cnt (구매 테이블에 해당 사용자가 구매한적이 있는지 확인 후 증가 시킴)   
	 @see com.paintee.mobile.purchase.service.PurchaseService#addPurchase(com.paintee.common.repository.entity.Purchase)
	*/
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		
		String userId = purchase.getUserId();
		String paintingId = purchase.getPaintingId();
		
		// 회원의 그림을 이전에 구매했는지 카운트를 조회
		PurchaseExample example = new PurchaseExample();
		example.createCriteria().andUserIdEqualTo    (userId)
		                        .andPaintingIdEqualTo(paintingId);
		int puchaseCount = purchaseHelper.countByExample(example);
		logger.debug("구매카운트 : {}", puchaseCount);
		
		// 구매 테이블 데이터 추가
		purchaseHelper.insertSelective(purchase);
		
		// 회원 테이블 정보 추가 - 구매카운트(post_cnt), 수익 전체 금액(earn_total_money)
		User user = new User();
		user.setUserId(userId);
		userHelper.updateUserPostCnt(user);
		userHelper.updateUserEarnTotalMoney(purchase);
		
		// 그림 테이블 정보 업데이트 - posted_num 무조건 1 증가, posted_people_cnt (구매 테이블에 해당 사용자가 산적이 있는지 확인 후 증가 시킴)
		Painting painting = new Painting();
		painting.setPaintingId(paintingId);
		if (puchaseCount == 0) {
			painting.setPostedPeopleCnt(0);
		}
		paintingHelper.updatePaintingPurchaseInfo(painting);
	}
}






