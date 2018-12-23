package com.betmatrix.theonex.dao.br.dao;

import com.betmatrix.theonex.dao.br.model.BrMatchMarketArgument;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrMatchMarketArgumentMapper {
    int insert(BrMatchMarketArgument record);

    int insertSelective(BrMatchMarketArgument record);

    BrMatchMarketArgument selectByPrimaryKey(Integer matchMarketArgumentId);

    int updateByPrimaryKeySelective(BrMatchMarketArgument record);

    int updateByPrimaryKey(BrMatchMarketArgument record);

    @Select("SELECT * FROM MatchMarketArgument WHERE matchId = #{matchId}")
    List<BrMatchMarketArgument> findByMarketId(Long matchId);


    @Delete("DELETE FROM MatchMarketArgument WHERE matchMarketArgumentId = #{matchMarketArgumentId} ")
    void deleteByMatchMarketArgumentId(Integer matchMarketArgumentId);
}