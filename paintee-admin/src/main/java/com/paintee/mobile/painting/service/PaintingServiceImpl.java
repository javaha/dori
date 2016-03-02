/**
@file PaintingServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PaintingServiceImpl.java |    
| Package | com.paintee.mobile.painting.service |    
| Project name | paintee-admin |    
| Type name | PaintingServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 2. 오후 10:59:36 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.painting.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.PaintingHelper;

/**
@class PaintingServiceImpl
com.paintee.mobile.painting.service \n
   ㄴ PaintingServiceImpl.java
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
@Service(value="com.paintee.mobile.painting.service.PaintingServiceImpl")
public class PaintingServiceImpl implements PaintingService {
	private final static Logger logger = LoggerFactory.getLogger(PaintingServiceImpl.class);

	@Autowired
	private PaintingHelper paintingHelper;

	@Autowired
	private FileInfoHelper fileInfoHelper;

	public Map<String, Object> getPaintingInfo(String paintingId) throws Exception {
		Painting painting = paintingHelper.selectByPrimaryKey(paintingId);

		logger.debug("painting2:{}", painting);

		Map<String, Object> resultMap = BeanUtils.describe(painting);

		logger.debug("resultMap2:{}", resultMap);

		//파일정보 조회
		FileInfoExample fileInfoExample = new FileInfoExample();
		FileInfoExample.Criteria where = fileInfoExample.createCriteria();
		where.andFileGroupSeqEqualTo(painting.getFileGroupSeq());

		List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);

		if(fileInfoList != null && fileInfoList.size() > 0) {
			FileInfo fileInfo = fileInfoList.get(0);

			resultMap.put("fileId", fileInfo.getId());
		}

		return resultMap;
	}
}
