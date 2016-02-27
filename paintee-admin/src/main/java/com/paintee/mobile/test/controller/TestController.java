/**
@file TestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | TestController.java |    
| Package | com.paintee.mobile.test.controller |    
| Project name | paintee-admin |    
| Type name | TestController |    
| Company | SAMSUNG | 
| Create Date | 2016 2016. 2. 27. 오후 4:38:55 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
@class TestController
com.paintee.mobile.test.controller \n
   ㄴ TestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | SAMSUNG |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 4:38:55 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@RestController(value="com.paintee.mobile.test.TestController")
public class TestController {
	/**
	 @fn test
	 @brief 함수 간략한 설명 : json 데이터 전송 테스트용 json 데이터 전송
	 @remark
	 - 함수의 상세 설명 : json 데이터 전송 테스트용 json 데이터 전송
	 @return 
	*/
	@RequestMapping(value="/ajax/test", method={RequestMethod.GET})
	public Map<String, Object> test() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("test", "aaa");
		resultMap.put("ccc", 1);

		return resultMap;
	}
}
