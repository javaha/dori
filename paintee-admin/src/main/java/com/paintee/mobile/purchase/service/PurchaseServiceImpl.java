/**
@file PurchaseServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseServiceImpl.java |    
| Package | com.paintee.mobile.purchase.service |    
| Project name | paintee-admin |    
| Type name | PurchaseServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 1:54:10 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.purchase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.PurchaseExample;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PaintingVO;
import com.paintee.common.repository.entity.vo.PurchaseSearchVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.PaintingHelper;
import com.paintee.common.repository.helper.PurchaseHelper;
import com.paintee.common.repository.helper.UserHelper;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class PurchaseServiceImpl
com.paintee.mobile.purchase.service \n
   ㄴ PurchaseServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 1:54:10 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@Service(value="com.paintee.mobile.purchase.service.PurchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	private final static Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);
	
	@Autowired
	private PurchaseHelper purchaseHelper;
	
	@Autowired
	private FileInfoHelper fileInfoHelper;
	
	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private PaintingHelper paintingHelper;
	
	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 그림 구매시 해야할 일
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	   1. 구매테이블에 데이터를 입력한다.
	   2. 회원 테이블 정보 업데이트 
	      - 구매자의 구매카운트(post_cnt) 증가
	   3. 그림 테이블 정보 업데이트 
	      - posted_num 무조건 1 증가, 
	      - posted_people_cnt (구매 테이블에 해당 사용자가 구매한적이 있는지 확인 후 증가 시킴)   
	   --------------------------------------------------------------------
	 @see com.paintee.mobile.purchase.service.PurchaseService#addPurchase(com.paintee.common.repository.entity.Purchase)
	*/
	@Override
	public Map<String, Object> addPurchase(PurchaseSearchVO purchase) throws Exception {
		logger.debug("구매추가 : {}", purchase);

		String userId = purchase.getUserId();
		String paintingId = purchase.getPaintingId();
		
		// 회원의 그림을 이전에 구매했는지 카운트를 조회
		PurchaseExample example = new PurchaseExample();
		example.createCriteria().andUserIdEqualTo    (userId)
		                        .andPaintingIdEqualTo(paintingId);
		int puchaseCount = purchaseHelper.countByExample(example);
		logger.debug("구매카운트 : {}", puchaseCount);
		
		// 구매 테이블 데이터 추가
		purchaseHelper.insertSelective(purchase);
		
		// 그림 테이블 정보 업데이트 - posted_num 무조건 1 증가, posted_people_cnt (구매 테이블에 해당 사용자가 산적이 있는지 확인 후 증가 시킴)
		Painting painting = new Painting();
		painting.setPaintingId(paintingId);
		if (puchaseCount == 0) {
			painting.setPostedPeopleCnt(1);
		} else {
			painting.setPostedPeopleCnt(0);
		}
		painting.setPostedNum(1);
		paintingHelper.updatePaintingPurchaseInfo(painting);
		
		// 회원 테이블 정보 추가 - 구매카운트(post_cnt)
		User user = new User();
		user.setUserId(userId);
		
		if ("Y".equalsIgnoreCase(purchase.getChangeAddr())) {
			user.setZipcode(purchase.getReceiverZipcode());
			user.setBasicAddr(purchase.getReceiverBasicAddr());
			user.setDetailAddr(purchase.getReceiverDetailAddr());
		}
		
		user.setResentSendZipcode(purchase.getReceiverZipcode());
		user.setResentSendBasicAddr(purchase.getReceiverBasicAddr());
		user.setResentSendDetailAddr(purchase.getReceiverDetailAddr());
		user.setResentSendCity(purchase.getReceiverCity());
		user.setResentSendName(purchase.getReceiverName());
		
		user.setPostCnt(1);
		
		userHelper.updateUserInfo(user);
		
		// 구매 후 공유를 할 수 있게 하기 위해 그림 정보를 가져온다.
		Map<String, Object> resultMap = new HashMap<>();
		PaintingVO pInfo = paintingHelper.selectPaintingInfo(paintingId);
		
		//파일정보 조회
		FileInfoExample fileInfoExample = new FileInfoExample();
		FileInfoExample.Criteria where = fileInfoExample.createCriteria();
		where.andFileGroupSeqEqualTo(pInfo.getFileGroupSeq());

		List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);

		if(fileInfoList != null && fileInfoList.size() > 0) {
			FileInfo fileInfo = fileInfoList.get(0);
			resultMap.put("fileId", fileInfo.getId());
		}
		return resultMap;
	}

	@Override
	public User purchasePopInfo(LoginedUserVO loginedUserVO) {
		return userHelper.selectByPrimaryKey(loginedUserVO.getUserId());
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	   1. 구매테이블에 데이터를 삭제한다.
	   2. 회원 테이블 정보 업데이트 
	      - 구매자의 구매카운트(post_cnt) 1 감소
	   3. 그림 테이블 정보 업데이트 
	      - posted_num 무조건 1 감소, 
	      - posted_people_cnt (구매 테이블에 해당 사용자가 구매한적이 있는지 확인 후 감소 시킴)   
	   --------------------------------------------------------------------
	 @see com.paintee.mobile.purchase.service.PurchaseService#cancelPurchase(com.paintee.common.repository.entity.vo.PurchaseSearchVO)
	*/
	@Override
	public Map<String, Object> cancelPurchase(PurchaseSearchVO purchase) {
		
		String userId = purchase.getUserId();
		String paintingId = purchase.getPaintingId();
		
		// 회원의 그림을 이전에 구매했는지 카운트를 조회
		// 구매상태가 요청-1/발송-2/환불요청-3/재발송요청-4/재발송처리-5/환불처리-6/삭제-7 
		List<String> statusList = new ArrayList<>();
		statusList.add("1");
		statusList.add("2");
		statusList.add("3");
		statusList.add("4");
		statusList.add("5");
		statusList.add("99");
		PurchaseExample example = new PurchaseExample();
		example.createCriteria().andUserIdEqualTo    (userId)
		                        .andPaintingIdEqualTo(paintingId)
		                        .andPurchaseStatusIn(statusList);
		int puchaseCount = purchaseHelper.countByExample(example);
		logger.debug("구매카운트 : {}", puchaseCount);
		
		// 그림 테이블 정보 업데이트 - posted_num 무조건 1 감소, 
		// posted_people_cnt (구매 테이블에 해당 사용자가 그림을 1번만 구매한 경우 확인 후 감소 시킴)
		Painting painting = new Painting();
		painting.setPaintingId(paintingId);
		if (puchaseCount == 1) {
			painting.setPostedPeopleCnt(-1);
		} else {
			painting.setPostedPeopleCnt(0);
		}
		painting.setPostedNum(-1);
		paintingHelper.updatePaintingPurchaseInfo(painting);
		
		// 회원 테이블 정보 추가 - 구매카운트(post_cnt) 감소
		User user = new User();
		user.setUserId(userId);
		user.setPostCnt(-1);
		userHelper.updateUserInfo(user);
		
		// 구매정보 삭제
		purchaseHelper.deleteByPrimaryKey(purchase.getSeq());
		
		Map<String, Object> result = new HashMap<>();
		// 에러정보
		result.put("errorNo", 0);
		result.put("errorMsg", "");
		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 구매상태를 재발송요청으로 변경한다. 
	 @see com.paintee.mobile.purchase.service.PurchaseService#updateStatusPurchase(com.paintee.common.repository.entity.vo.PurchaseSearchVO)
	*/
	@Override
	public Map<String, Object> updateStatusPurchase(PurchaseSearchVO purchase) {
		purchaseHelper.updateByPrimaryKeySelective(purchase);
		
		Map<String, Object> result = new HashMap<>();
		// 에러정보
		result.put("errorNo", 0);
		result.put("errorMsg", "");
		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	   My 그림 목록에서 로그인 사용자가 자신의 업로드된 그림을 삭제요청 했을 때 처리
		   1. 작가의 업로드 카운트 감소 
		   2. 구매 그림의 상태 변경
		  
	 @see com.paintee.mobile.purchase.service.PurchaseService#delStatusPainting(com.paintee.common.repository.entity.Painting)
	*/
	@Override
	public Map<String, Object> delStatusPainting(Painting painting) {
		// 작가의 업로드 카운트 감소
		User user = new User();
		user.setUploadCnt(-1);
		user.setUserId(painting.getArtistId());
		userHelper.updateUserInfo(user);
		
		// 구매 그림의 상태 변경
		paintingHelper.updateByPrimaryKeySelective(painting);
		
		Map<String, Object> result = new HashMap<>();
		// 에러정보
		result.put("errorNo", 0);
		result.put("errorMsg", "");
		return result;
	}

	@Override
	public Map<String, Object> delStatusPurchase(PurchaseSearchVO purchase) {
		User user = new User();
		user.setUserId(purchase.getUserId());
		user.setPostCnt(-1);
		userHelper.updateUserInfo(user);

		// 1. 구매테이블의 상태를 변경
		purchase.setStatusUpdateDate(new Date());
		purchaseHelper.updateByPrimaryKeySelective(purchase);
		
		Map<String, Object> result = new HashMap<>();
		// 에러정보
		result.put("errorNo", 0);
		result.put("errorMsg", "");
		return result;
	}
}






