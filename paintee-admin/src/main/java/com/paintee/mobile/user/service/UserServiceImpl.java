/**
@file UserServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | UserServiceImpl.java |    
| Package | com.paintee.mobile.user.service |    
| Project name | paintee-admin |    
| Type name | UserServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:46:48 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
@class UserServiceImpl
com.paintee.mobile.user.service \n
   ㄴ UserServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:46:48 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 service 구현채
*/
@Service(value="com.paintee.mobile.user.service.UserServiceImpl")
public class UserServiceImpl implements UserService {
	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

}
