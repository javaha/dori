/**
@file PaintingHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PaintingHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | PaintingHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 11:00:26 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.mapper.PaintingMapper;

/**
@class PaintingHelper
com.paintee.common.repository.helper \n
   ㄴ PaintingHelper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 11:00:26 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림에 대한 helper
*/
public interface PaintingHelper extends PaintingMapper {
	
	/**
	 @fn updatePaintingPurchaseInfo
	 @brief 함수 간략한 설명 : 그림의 구매 정보를 업데이트
	 @remark
	 - 함수의 상세 설명 : 그림의 구매 카운트 및 구매자 카운트 정보를 증가시킨다.
	 @param painting 
	*/
	public void updatePaintingPurchaseInfo(Painting painting);
}
