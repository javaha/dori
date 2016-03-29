/**
@file PersonalService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PersonalService.java |    
| Package | com.paintee.mobile.follow.service |    
| Project name | paintee-admin |    
| Type name | PersonalService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:24:07 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.personal.service;

import java.util.Map;

import com.paintee.common.repository.entity.vo.PersonalSearchVO;

/**
@class PersonalService
com.paintee.mobile.follow.service \n
   ㄴ PersonalService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:24:07 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - follow service
*/
public interface PersonalService {

	/**
	 @fn getPersonalPaintingInfo
	 @brief 함수 간략한 설명 : 특정 작가의 개인 페이지 정보 조회
	 @remark
	 - 함수의 상세 설명 : 특정 작가의 업로드 카운트와 업로드 그림 정보를 조회한다.
	 @param search
	 @return 
	*/
	public Map<String, Object> getPersonalPaintingInfo(PersonalSearchVO searchVO);
}
