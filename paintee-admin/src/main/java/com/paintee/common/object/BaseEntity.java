/**
@file BaseEntity.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | BaseEntity.java |    
| Package | com.paintee.common.object |    
| Project name | paintee-admin |    
| Type name | BaseEntity |    
| Company | SAMSUNG | 
| Create Date | 2016 2016. 2. 27. 오후 3:56:54 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.object;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
@class BaseEntity
com.paintee.common.object \n
   ㄴ BaseEntity.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | SAMSUNG |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 3:56:54 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 최상위 entity
*/
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -6054473880554054842L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this,	ToStringStyle.MULTI_LINE_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
