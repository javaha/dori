/**
@file SignUpService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | SignUpService.java |    
| Package | com.paintee.mobile.auth.service |    
| Project name | paintee-admin |    
| Type name | SignUpService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 12. 오후 11:02:18 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.auth.service;

import com.paintee.common.repository.entity.User;

/**
@class SignUpService
com.paintee.mobile.auth.service \n
   ㄴ SignUpService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 12. 오후 11:02:18 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 회원가입 service
*/
public interface SignUpService {
	/**
	 @fn registUser
	 @brief 함수 간략한 설명 : 회원가입
	 @remark
	 - 함수의 상세 설명 : 회원가입
	 @param user
	 @return 
	*/
	public boolean registUser(User user);

	/**
	 @fn confirmHsh
	 @brief 함수 간략한 설명 : hash 정보를 통해 사용자계정 활성화
	 @remark
	 - 함수의 상세 설명 : hash 정보를 통해 사용자계정을 활성화시킨다.(0:정상, 1:해당 정보를 찾지 못한경우, 2:expire date 가 지난경우)
	 @param hash
	 @return 
	*/
	public int confirmHsh(String hash);
}
