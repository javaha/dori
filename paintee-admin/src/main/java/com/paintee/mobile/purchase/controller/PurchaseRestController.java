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

import com.paintee.common.repository.entity.Purchase;
import com.paintee.mobile.purchase.service.PurchaseService;

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
	
	@RequestMapping(value="/api/purchase", method={RequestMethod.POST})
	public Map<String, Object> addPurchase(@RequestBody Purchase purchase) throws Exception {
		logger.debug(purchase.toString());
		
		// 구매관련 정보 등록
		purchaseService.addPurchase(purchase);
		
		Map<String, Object> result = new HashMap<>();
		result.put("msg", "success");
		return result;
	}
}













