package com.bwl.mapper;

import com.bwl.pojo.Content;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {

    List<Content> selectContentId(int id);

    int insertContent(Content content);

    int selectContent(Content content);

    int updateContent(Content content);

    int deleteByIdInt(int id);
}
