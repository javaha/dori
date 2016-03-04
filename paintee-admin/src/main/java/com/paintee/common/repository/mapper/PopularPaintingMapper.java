package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.PopularPainting;
import com.paintee.common.repository.entity.PopularPaintingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PopularPaintingMapper {
    int countByExample(PopularPaintingExample example);

    int deleteByExample(PopularPaintingExample example);

    int deleteByPrimaryKey(Integer seq);

    int insert(PopularPainting record);

    int insertSelective(PopularPainting record);

    List<PopularPainting> selectByExample(PopularPaintingExample example);

    PopularPainting selectByPrimaryKey(Integer seq);

    int updateByExampleSelective(@Param("record") PopularPainting record, @Param("example") PopularPaintingExample example);

    int updateByExample(@Param("record") PopularPainting record, @Param("example") PopularPaintingExample example);

    int updateByPrimaryKeySelective(PopularPainting record);

    int updateByPrimaryKey(PopularPainting record);
}