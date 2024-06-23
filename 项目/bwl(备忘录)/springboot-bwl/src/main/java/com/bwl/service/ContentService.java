package com.bwl.service;

import com.bwl.mapper.ContentMapper;
import com.bwl.pojo.Content;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    @Resource
    private ContentMapper contentMapper;

    public List<Content> selectContentId(int id){
        return contentMapper.selectContentId(id);
    }

    public int insertContent(Content content){
        contentMapper.insertContent(content);
        return contentMapper.selectContent(content);
    }

    public int updateContent(Content content){
        return contentMapper.updateContent(content);
    }

    public int deleteById(int id){
        return contentMapper.deleteByIdInt(id);
    }
}
