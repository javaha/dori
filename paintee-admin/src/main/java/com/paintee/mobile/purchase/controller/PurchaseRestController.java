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
package com.paintee.mobile.purchase.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PurchaseSearchVO;
import com.paintee.mobile.purchase.service.PurchaseService;
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
@RestController(value="com.paintee.mobile.purchase.controller.PurchaseRestController")
public class PurchaseRestController {
	
	private final static Logger logger = LoggerFactory.getLogger(PurchaseRestController.class);
	
	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping(value="/api/purchasePopInfo", method={RequestMethod.GET})
	public Map<String, Object> purchasePopInfo(LoginedUserVO loginedUserVO) throws Exception {
		
		// 구매관련 정보 등록
		User user = purchaseService.purchasePopInfo(loginedUserVO);
		
		Map<String, Object> result = new HashMap<>();
		result.put("user", user);
		return result;
	}
	
	@RequestMapping(value="/api/purchase", method={RequestMethod.POST})
	public Map<String, Object> addPurchase(@RequestBody PurchaseSearchVO purchase, LoginedUserVO loginedUserVO) throws Exception {
		logger.debug(purchase.toString());

		// 로그인 사용자의 구매자 아이디 입력
		purchase.setUserId(loginedUserVO.getUserId());
		
		// 구매관련 정보 등록
		Map<String, Object> result = purchaseService.addPurchase(purchase);
		return result;
	}
	
	@RequestMapping(value={"/api/resendStatusPurchase", "/api/cancelPurchase", "/api/completeStatusPurchase", "/api/cancelRefundPurchase"}, method={RequestMethod.POST})
	public Map<String, Object> resendPurchase(@RequestBody PurchaseSearchVO purchase) throws Exception {
		logger.debug(purchase.toString());
		// 구매관련 정보 등록
		Map<String, Object> result = purchaseService.updateStatusPurchase(purchase);
		return result;
	}
	
	/**
	 @fn delStatusPainting
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 마이페이지에서 그림을 삭제 요청 했을때 처리
	 @param loginedUserVO
	 @return
	 @throws Exception 
	*/
	@RequestMapping(value="/api/delStatusPainting", method={RequestMethod.POST})
	public Map<String, Object> delStatusPainting(@RequestBody Painting painting, LoginedUserVO loginedUserVO) throws Exception {
		
		Map<String, Object> result = purchaseService.delStatusPainting(painting);
		return result;
	}

	/**
	 @fn delStatusPurchase
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 마이페이지에서 그림을 삭제 요청 했을때 처리
	 @param loginedUserVO
	 @return
	 @throws Exception 
	 */
	@RequestMapping(value="/api/delStatusPurchase", method={RequestMethod.POST})
	public Map<String, Object> delStatusPurchase(@RequestBody PurchaseSearchVO purchase) throws Exception {
		
		Map<String, Object> result = purchaseService.delStatusPurchase(purchase);
		return result;
	}
}













