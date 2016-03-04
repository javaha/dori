/**
@file UserService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | UserService.java |    
| Package | com.paintee.mobile.user.service |    
| Project name | paintee-admin |    
| Type name | UserService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:46:22 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.user.service;

/**
@class UserService
com.paintee.mobile.user.service \n
   ㄴ UserService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:46:22 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 service
*/
public interface UserService {
	/**
	 @fn follow
	 @brief 함수 간략한 설명 : followId 의 사용자가 followingId 사용자를 follow 한다.
	 @remark
	 - 함수의 상세 설명 : followId 의 사용자가 followingId 사용자를 follow 한다.
	 @param followId
	 @param followingId
	 @return 
	*/
	public int follow(String followId, String followingId);
}
