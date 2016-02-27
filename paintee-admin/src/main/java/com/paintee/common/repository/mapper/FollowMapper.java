package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.FollowExample;
import com.paintee.common.repository.entity.FollowKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FollowMapper {
    int countByExample(FollowExample example);

    int deleteByExample(FollowExample example);

    int deleteByPrimaryKey(FollowKey key);

    int insert(FollowKey record);

    int insertSelective(FollowKey record);

    List<FollowKey> selectByExample(FollowExample example);

    int updateByExampleSelective(@Param("record") FollowKey record, @Param("example") FollowExample example);

    int updateByExample(@Param("record") FollowKey record, @Param("example") FollowExample example);
}