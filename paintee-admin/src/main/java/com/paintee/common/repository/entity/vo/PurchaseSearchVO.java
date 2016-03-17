/**
@file PurchaseSearchVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseSearchVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | PurchaseSearchVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 9:15:35 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

import com.paintee.common.repository.entity.Purchase;

/**
@class PurchaseSearchVO
com.paintee.common.repository.entity.vo \n
   ㄴ PurchaseSearchVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 9:15:35 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 구매한 사용자에 대한 정보 관련 vo
*/
public class PurchaseSearchVO extends Purchase {
	private static final long serialVersionUID = -3180373314948888877L;

	private String changeAddr;

	public String getChangeAddr() {
		return changeAddr;
	}

	public void setChangeAddr(String changeAddr) {
		this.changeAddr = changeAddr;
	}
}
