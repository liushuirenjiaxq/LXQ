package com.bwl.controller;

import com.bwl.pojo.Content;
import com.bwl.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "备忘数据接口")
@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/content")
public class ContentController extends BaseController{

    @Operation(summary = "查询备忘录", description = "输入用户id,查询该用户的备忘录的信息数据")
    @GetMapping("/{id}")
    public Result selectContentAll(@PathVariable("id") Integer id){
        List<Content> list = contentService.selectContentId(id);
        //System.out.println(list);
        return Result.data(list);
    }

    @Operation(summary = "新增备忘录", description = "输入Content对象数据，新增用户的备忘录信息，并将id返回")
    @PostMapping("/in")
    public Result insertContent(@RequestBody Content content){
        int flag = contentService.insertContent(content);
        return Result.data(flag);
    }

    @Operation(summary = "修改备忘录", description = "输入Content对象数据，通过id修改数据")
    @PostMapping("/update")
    public Result updateContent(@RequestBody Content content){
        contentService.updateContent(content);
        return Result.success();
    }

    @Operation(summary = "删除备忘录", description = "输入id,删除备忘录信息")
    @GetMapping("/del/{id}")
    public Result deleteContent(@PathVariable("id") Integer id){
        contentService.deleteById(id);
        return Result.success();
    }
}
