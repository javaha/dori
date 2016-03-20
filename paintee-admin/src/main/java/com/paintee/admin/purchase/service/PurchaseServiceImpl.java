/**
@file PurchaseServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PurchaseServiceImpl.java |    
| Package | com.paintee.mobile.painting.service |    
| Project name | paintee-admin |    
| Type name | PurchaseServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 10:59:36 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.purchase.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.vo.PaintingVO;
import com.paintee.common.repository.entity.vo.PurchaseSearchVO;
import com.paintee.common.repository.entity.vo.PurchaseVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.PurchaseHelper;

/**
@class PurchaseServiceImpl
com.paintee.mobile.painting.service \n
   ㄴ PurchaseServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 10:59:36 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림에 대한 service 구현채
*/
@Service(value="com.paintee.admin.purchase.service.PurchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	private final static Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);
	
	@Autowired
	private PurchaseHelper purchaseHelper;
	
	@Override
	public Map<String, Object> getPurchaseList(PurchaseSearchVO search) {
		
		List<PurchaseVO> list = purchaseHelper.selectPurchaseList(search);
		logger.debug("list : " + list);
		
		int count = purchaseHelper.selectPurchaseListCount(search);
		logger.debug("전체 개수 : " + count);
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("count", count);
		return result;
	}
}
