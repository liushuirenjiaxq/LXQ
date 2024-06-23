package com.bwl.mapper;

import com.bwl.pojo.BackLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackLogMapper {

    List<BackLog> selectBackLogById(int id);

    int insertBakcLog(BackLog backLog);

    int updateStateById(BackLog backLog);

    int updateDelById(int id);

    int updateTextById(BackLog backLog);

    int selectBackLog(BackLog backLog);
}
