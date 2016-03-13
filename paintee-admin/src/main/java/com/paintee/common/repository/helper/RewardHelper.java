/**
@file RewardHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | RewardHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | RewardHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 3. 오후 11:09:41 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.RewardVO;
import com.paintee.common.repository.mapper.RewardMapper;

/**
@class RewardHelper
com.paintee.common.repository.helper \n
   ㄴ RewardHelper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 3. 오후 11:09:41 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 구매정보 helper
*/
public interface RewardHelper extends RewardMapper {
	
	/**
	 @fn selectRewardInfo
	 @brief 함수 간략한 설명 : 리워드 정보를 조회
	 @remark
	 - 함수의 상세 설명 : 특정 사용자의 리워드와 연관된 정보를 조회한다.
	 @param user
	 @return 
	*/
	public RewardVO selectRewardInfo(User user);
}
