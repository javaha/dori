/**
@file RewardController.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | RewardController.java |    
| Package | com.paintee.admin.test.controller |    
| Project name | paintee-admin |    
| Type name | RewardController |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 5:14:46 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.reward.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paintee.admin.reward.service.RewardService;
import com.paintee.common.paging.PageVO;
import com.paintee.common.repository.entity.Reward;
import com.paintee.common.repository.entity.vo.RewardSearchVO;

/**
@class RewardController
com.paintee.admin.test.controller \n
   ㄴ RewardController.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 5:14:46 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - jsp view 를 포함한 controller
*/
@Controller(value="com.paintee.admin.reward.RewardController")
@RequestMapping(value="/admin/reward")
public class RewardController {

	@Autowired
	private RewardService rewardService;
	
	/**
	 @fn test
	 @brief 함수 간략한 설명 : test view 화면
	 @remark
	 - 함수의 상세 설명 : test view 화면
	 @return 
	*/
	@RequestMapping(value="/list", method={RequestMethod.GET})
	public void list(@RequestParam(name="pageNo", required=false, defaultValue="1") Integer pageNo, Model model) {
		// 데이터 조건 설정
		RewardSearchVO search = new RewardSearchVO();
		search.setStartRow((pageNo - 1) * 5);
		search.setRowPerPage(10);
		// 목록에 조회할 상태
		List<String> rewardStatus = new ArrayList<>();
		rewardStatus.add("R");  // 요청
		search.setRewardStatus(rewardStatus);
		
		Map<String, Object> result = rewardService.getRewardList(search);
		
		// 화면 페이징 처리
		PageVO pageVO = new PageVO(
				"/admin/reward/list", pageNo, 
				(int)result.get("count"), (List<Object>)result.get("list"));
		model.addAttribute(pageVO);
	}
	
	/**
	 @fn modReward
	 @brief 함수 간략한 설명 : 리워드 상태 변경
	 @remark
	 - 함수의 상세 설명 : 리워드 상태 변경시 처리
	 @return 
	 */
	@RequestMapping(value="/mod", method={RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> modReward(Reward reward) {
		rewardService.modRewardStatus(reward);
		
		int money = reward.getEarmRequestedCommission() + reward.getEarmRequestedMoney();
		System.out.println("money ::: " + money);
		
		Map<String, Object> result = new HashMap<>();
		result.put("msg", "상태가 변경되었습니다.");
		return result;
	}
	/*
	@RequestMapping(value="/mod", method={RequestMethod.POST})
	public String modReward(@RequestParam(name="pageNo", required=false, defaultValue="1") Integer pageNo, 
			Reward reward, RedirectAttributes model) {
		System.out.println("reward ::: " + reward);
		rewardService.modRewardStatus(reward);
		
		model.addFlashAttribute("msg", "상태가 변경되었습니다.");
		return "redirect:/admin/reward/list";
	}
	*/
}



















