/**
@file PurchaseService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseService.java |    
| Package | com.paintee.mobile.purchase.service |    
| Project name | paintee-admin |    
| Type name | PurchaseService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 1:43:55 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.purchase.service;

import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PurchaseSearchVO;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class PurchaseService
com.paintee.mobile.purchase.service \n
   ㄴ PurchaseService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 1:43:55 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
public interface PurchaseService {
	
	/**
	 @fn purchasePopInfo
	 @brief 함수 간략한 설명 : 
	 @remark
	 - 함수의 상세 설명 : 
	 @param loginedUserVO
	 @return 
	*/
	public User purchasePopInfo(LoginedUserVO loginedUserVO);

	/**
	 @fn addPurchase
	 @brief 함수 간략한 설명 : 구매정보를 등록한다.
	 @remark
	 - 함수의 상세 설명 : 구매정보를 등록한다. 
	 @param purchase
	 @throws Exception 
	*/
	public void addPurchase(PurchaseSearchVO purchase) throws Exception;
}
