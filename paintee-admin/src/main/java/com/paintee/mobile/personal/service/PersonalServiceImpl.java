/**
@file PersonalServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PersonalServiceImpl.java |    
| Package | com.paintee.mobile.follow.service |    
| Project name | paintee-admin |    
| Type name | PersonalServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:24:22 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.personal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paintee.common.repository.entity.FileInfo;
import com.paintee.common.repository.entity.FileInfoExample;
import com.paintee.common.repository.entity.vo.PersonalSearchVO;
import com.paintee.common.repository.entity.vo.PersonalVO;
import com.paintee.common.repository.helper.FileInfoHelper;
import com.paintee.common.repository.helper.PersonalHelper;

/**
@class PersonalServiceImpl
com.paintee.mobile.follow.service \n
   ㄴ PersonalServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:24:22 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - follow service 구현채
*/
@Service(value="com.paintee.mobile.follow.service.PersonalServiceImpl")
public class PersonalServiceImpl implements PersonalService {
	private final static Logger logger = LoggerFactory.getLogger(PersonalServiceImpl.class);

	@Autowired
	private PersonalHelper personalHelper;
	
	@Autowired
	private FileInfoHelper fileInfoHelper;
	
	@Override
	public Map<String, Object> getPersonalPaintingInfo(PersonalSearchVO searchVO) {
		
		// 개인 페이지 정보
		PersonalVO personalVO = personalHelper.selectPersonalPaintingInfo(searchVO);
		
		List<PersonalVO> list = personalHelper.selectPersonalPaintingList(searchVO);
		logger.debug("list ::: {}", list);
		
		// 파일정보 조회
		for (PersonalVO personal : list) {
			FileInfoExample fileInfoExample = new FileInfoExample();
			FileInfoExample.Criteria fileWhere = fileInfoExample.createCriteria();
			fileWhere.andFileGroupSeqEqualTo(personal.getFileGroupSeq());
	
			List<FileInfo> fileInfoList = fileInfoHelper.selectByExample(fileInfoExample);
	
			if(fileInfoList != null && fileInfoList.size() > 0) {
				FileInfo fileInfo = fileInfoList.get(0);
				personal.setFileId(fileInfo.getId());
			}
		}
		
		Map<String, Object> result = new HashMap<>();
		String artistId = personalVO.getArtistId();
		String paintingId = personalVO.getPaintingId();
		if (artistId == null) {
			result.put("errorNo", "100");
			return result;
		} 
		result.put("personal", personalVO);
		result.put("list", list);
		return result;
	}
}
