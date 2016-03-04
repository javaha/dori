package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.NewPainting;
import com.paintee.common.repository.entity.NewPaintingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewPaintingMapper {
    int countByExample(NewPaintingExample example);

    int deleteByExample(NewPaintingExample example);

    int deleteByPrimaryKey(Integer seq);

    int insert(NewPainting record);

    int insertSelective(NewPainting record);

    List<NewPainting> selectByExample(NewPaintingExample example);

    NewPainting selectByPrimaryKey(Integer seq);

    int updateByExampleSelective(@Param("record") NewPainting record, @Param("example") NewPaintingExample example);

    int updateByExample(@Param("record") NewPainting record, @Param("example") NewPaintingExample example);

    int updateByPrimaryKeySelective(NewPainting record);

    int updateByPrimaryKey(NewPainting record);
}