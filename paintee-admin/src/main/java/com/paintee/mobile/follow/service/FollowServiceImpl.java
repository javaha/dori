/**
@file FollowServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FollowServiceImpl.java |    
| Package | com.paintee.mobile.follow.service |    
| Project name | paintee-admin |    
| Type name | FollowServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:24:22 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.follow.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
@class FollowServiceImpl
com.paintee.mobile.follow.service \n
   ㄴ FollowServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:24:22 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - follow service 구현채
*/
@Service(value="com.paintee.mobile.follow.service.FollowServiceImpl")
public class FollowServiceImpl implements FollowService {
	private final static Logger logger = LoggerFactory.getLogger(FollowServiceImpl.class);

}
