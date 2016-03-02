/**
@file PaintingRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PaintingRestController.java |    
| Package | com.paintee.mobile.painting.controller |    
| Project name | paintee-admin |    
| Type name | PaintingRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 10:54:46 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.painting.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.mobile.painting.service.PaintingService;

/**
@class PaintingRestController
com.paintee.mobile.painting.controller \n
   ㄴ PaintingRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 10:54:46 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림에 대한 RestController
*/
@RestController(value="com.paintee.mobile.painting.controller.PaintingRestController")
public class PaintingRestController {
	private final static Logger logger = LoggerFactory.getLogger(PaintingRestController.class);

	@Autowired
	private PaintingService paintingService;

	/**
	 @fn detailPainting
	 @brief 함수 간략한 설명 : 그림에 대한 정보조회
	 @remark
	 - 함수의 상세 설명 : 그림에 대한 정보를 조회한다.
	 @return 
	*/
	@RequestMapping(value="/api/painting/{paintingId}", method={RequestMethod.GET})
	public Map<String, Object> detailPainting(@PathVariable String paintingId) throws Exception {

		return paintingService.getPaintingInfo(paintingId);
	}
}
