/**
@file LoginedUserVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | LoginedUserVO.java |    
| Package | com.paintee.mobile.support |    
| Project name | paintee-admin |    
| Type name | LoginedUserVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 9. 오후 9:28:31 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.support.obejct;

import com.paintee.common.object.BaseObject;

/**
@class LoginedUserVO
com.paintee.mobile.support \n
   ㄴ LoginedUserVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 9. 오후 9:28:31 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 로그인된 사용자 정보 VO
*/
public class LoginedUserVO extends BaseObject {
	private static final long serialVersionUID = 6785289957363860751L;
	
    private String userId;

    private String email;

    private String name;

    private String introduce;

    private String address;

    private String location;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
