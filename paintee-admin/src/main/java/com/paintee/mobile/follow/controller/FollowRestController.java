/**
@file FollowRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FollowRestController.java |    
| Package | com.paintee.mobile.follow.controller |    
| Project name | paintee-admin |    
| Type name | FollowRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:23:44 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.follow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.mobile.follow.service.FollowService;

/**
@class FollowRestController
com.paintee.mobile.follow.controller \n
   ㄴ FollowRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:23:44 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - follow rest controller
*/
@RestController(value="com.paintee.mobile.follow.controller.FollowRestController")
public class FollowRestController {
	private final static Logger logger = LoggerFactory.getLogger(FollowRestController.class);

	@Autowired
	private FollowService followService;

}
