package com.paintee.mobile.news.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.PaintingExample;
import com.paintee.common.repository.entity.vo.NewPaintingVO;
import com.paintee.common.repository.entity.vo.PaintingSearchVO;
import com.paintee.common.repository.entity.vo.PopularVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.NewPaintingHelper;
import com.paintee.common.repository.helper.PaintingHelper;
import com.paintee.common.repository.helper.PopularHelper;

/**
@class PopularServiceImpl
com.paintee.mobile.popular.service \n
   ㄴ PopularServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 11:57:12 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@Service(value="com.paintee.mobile.news.service.NewPaintingServiceImpl")
public class NewPaintingServiceImpl implements NewPaintingService {
	private final static Logger logger = LoggerFactory.getLogger(NewPaintingServiceImpl.class);
	
	@Autowired
	private PaintingHelper paintingHelper;

	@Autowired
	private NewPaintingHelper newPaintingHelper;
	
	@Autowired
	private FileInfoHelper fileInfoHelper;
	
	@Override
	public Map<String, Object> getNewPatingInfo(PaintingSearchVO search) throws Exception {
		PaintingExample example = new PaintingExample();
		PaintingExample.Criteria where =  example.createCriteria();
		where.andPaintingStatusEqualTo("N");
		
		int count = paintingHelper.countByExample(example);
		logger.debug("전체 개수 : " + count);
		
		List<NewPaintingVO> list = newPaintingHelper.selectNewPaintingList(search);
		logger.debug("list : " + list);
		
		// 파일정보 조회
		for (NewPaintingVO newPainting : list) {
			FileInfoExample fileInfoExample = new FileInfoExample();
			FileInfoExample.Criteria fileWhere = fileInfoExample.createCriteria();
			fileWhere.andFileGroupSeqEqualTo(newPainting.getFileGroupSeq());
	
			List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);
	
			if(fileInfoList != null && fileInfoList.size() > 0) {
				FileInfo fileInfo = fileInfoList.get(0);
				newPainting.setFileId(fileInfo.getId());
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("count", count);
		result.put("list", list);
		return result;
	}

}













