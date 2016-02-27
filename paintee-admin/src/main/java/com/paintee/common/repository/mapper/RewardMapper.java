package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.Reward;
import com.paintee.common.repository.entity.RewardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RewardMapper {
    int countByExample(RewardExample example);

    int deleteByExample(RewardExample example);

    int insert(Reward record);

    int insertSelective(Reward record);

    List<Reward> selectByExample(RewardExample example);

    int updateByExampleSelective(@Param("record") Reward record, @Param("example") RewardExample example);

    int updateByExample(@Param("record") Reward record, @Param("example") RewardExample example);
}