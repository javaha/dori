/**
@file NewPaintingHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | NewPaintingHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | NewPaintingHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오전 12:36:48 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import java.util.List;

import com.paintee.common.repository.entity.vo.NewPaintingVO;
import com.paintee.common.repository.entity.vo.PaintingSearchVO;

/**
@class NewPaintingHelper
com.paintee.common.repository.helper \n
   ㄴ NewPaintingHelper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오전 12:36:48 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
public interface NewPaintingHelper {
	List<NewPaintingVO> selectNewPaintingList(PaintingSearchVO popular);
}
