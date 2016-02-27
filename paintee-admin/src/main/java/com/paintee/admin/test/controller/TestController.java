/**
@file TestController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | TestController.java |    
| Package | com.paintee.admin.test.controller |    
| Project name | paintee-admin |    
| Type name | TestController |    
| Company | SAMSUNG | 
| Create Date | 2016 2016. 2. 27. 오후 5:14:46 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
@class TestController
com.paintee.admin.test.controller \n
   ㄴ TestController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | SAMSUNG |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 5:14:46 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - jsp view 를 포함한 controller
*/
@Controller(value="com.paintee.admin.test.TestController")
public class TestController {
	/**
	 @fn test
	 @brief 함수 간략한 설명 : test view 화면
	 @remark
	 - 함수의 상세 설명 : test view 화면
	 @return 
	*/
	@RequestMapping(value="/admin/test/list", method={RequestMethod.GET})
	public String test(Model model) {
		model.addAttribute("msg", "hello ");

		return "test/list";
	}
}
