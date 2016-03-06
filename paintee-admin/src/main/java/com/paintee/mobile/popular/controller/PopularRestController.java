/**
@file PopularRestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PopularRestController.java |    
| Package | com.paintee.mobile.popular.controller |    
| Project name | paintee-admin |    
| Type name | PopularRestController |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 3. 오후 5:46:32 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.popular.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paintee.common.repository.entity.vo.PaintingSearchVO;
import com.paintee.mobile.popular.service.PopularService;

/**
@class PopularRestController
com.paintee.mobile.popular.controller \n
   ㄴ PopularRestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 3. 오후 5:46:32 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@RestController(value="com.paintee.mobile.popular.controller.PopularRestController")
public class PopularRestController {
	
	@Autowired
	private PopularService popularService;
	
	@RequestMapping(value="/api/popularIndex", method={RequestMethod.GET})
	public Map<String, Object> index(@RequestParam(name="startRow", required=false, defaultValue="0") Integer startRow) throws Exception {
		
		// 데이터 조건 설정
		PaintingSearchVO search = new PaintingSearchVO();
		search.setStartRow(startRow);
		search.setRowPerPage(5);
		search.setPaintingStatus("N");
		
		Map<String, Object> result = popularService.getPopularInfo(search);
		
		return result;
	}
}
