package com.bwl.service;

import com.bwl.mapper.BackLogMapper;
import com.bwl.pojo.BackLog;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackLogService {
    @Resource
    private BackLogMapper backLogMapper;

    public List<BackLog> selectBackLogById(int id){
        return backLogMapper.selectBackLogById(id);
    }

    public int insertBakcLog(BackLog backLog){
        backLogMapper.insertBakcLog(backLog);
        return backLogMapper.selectBackLog(backLog);
    }

    public int updateStateById(BackLog backLog){
        return backLogMapper.updateStateById(backLog);
    }

    public int updateTextById(BackLog backLog){
        return backLogMapper.updateTextById(backLog);
    }

    /*单条数据的删除*/
    public int updateDelById(int id){
        return backLogMapper.updateDelById(id);
    }
}
