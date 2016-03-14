/**
@file PersonalSearchVO.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PersonalSearchVO.java |    
| Package | com.paintee.common.repository.entity.vo |    
| Project name | paintee-admin |    
| Type name | PersonalSearchVO |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 5. 오후 9:15:35 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.entity.vo;

/**
@class PersonalSearchVO
com.paintee.common.repository.entity.vo \n
   ㄴ PersonalSearchVO.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 5. 오후 9:15:35 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림의 아티스트가 업로드한 그림에 대한 정보 관련 vo
*/
public class PersonalSearchVO extends PagingVO {
	private static final long serialVersionUID = -3180373314948888878L;

	private String artistName;

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
}
