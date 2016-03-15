/**
@file PersonalRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PersonalRestController.java |    
| Package | com.paintee.mobile.follow.controller |    
| Project name | paintee-admin |    
| Type name | PersonalRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:23:44 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.personal.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.vo.PersonalSearchVO;
import com.paintee.mobile.personal.service.PersonalService;

/**
@class PersonalRestController
com.paintee.mobile.follow.controller \n
   ㄴ PersonalRestController.java
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
@RestController(value="com.paintee.mobile.follow.controller.PersonalRestController")
public class PersonalRestController {
	private final static Logger logger = LoggerFactory.getLogger(PersonalRestController.class);

	@Autowired
	private PersonalService personalService;

	@RequestMapping(value="/api/index/personal", method=RequestMethod.GET)
	public Map<String, Object> personalInfo(PersonalSearchVO search) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		logger.debug("personalInfo ::: search:{}", search);

		int errorNo = 0;
		String errorMsg = "";
		
		// 요청 데이터 페이징 정보
		search.setRowPerPage(5);
		
		resultMap = personalService.getPersonalPaintingInfo(search);
		
		// 에러정보
		resultMap.put("errorNo", errorNo);
		resultMap.put("errorMsg", errorMsg);
		
		return resultMap;
	}
	

}
