/**
@file FollowService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FollowService.java |    
| Package | com.paintee.mobile.follow.service |    
| Project name | paintee-admin |    
| Type name | FollowService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:24:07 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.myhome.service;

import java.util.Map;

import com.paintee.common.repository.entity.vo.FollowSearchVO;
import com.paintee.common.repository.entity.vo.MyHomeSearchVO;
import com.paintee.common.repository.entity.vo.MyHomeVO;

/**
@class FollowService
com.paintee.mobile.follow.service \n
   ㄴ FollowService.java
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
public interface MyHomeService {

	/**
	 @fn getFollowPaintingInfo
	 @brief 함수 간략한 설명 : 인덱스 페이지의 follow 정보를 조회
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자의 팔로잉과 팔로워의 카운트를 조회한다.
	                         로그인한 사용자의 following 한 사용자들의 업로드 및 구매 그림 정보를 조회한다.
	 @param search
	 @return 
	*/
	public Map<String, Object> getMyHomePaintingInfo(MyHomeSearchVO search);
}
