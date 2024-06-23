package com.bwl.controller;

import com.bwl.pojo.BackLog;
import com.bwl.pojo.Content;
import com.bwl.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "待办数据接口")
@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/backlog")
public class BackLogController extends BaseController{

    @Operation(summary = "查询待办", description = "输入用户id,查询该用户的待办的信息数据")
    @GetMapping("/{id}")
    public Result selectBackLogInfo(@PathVariable("id") Integer id){
        List<BackLog> list = backLogService.selectBackLogById(id);
        return Result.data(list);
    }

    @Operation(summary = "新增待办", description = "新增用户的待办的信息")
    @PostMapping("/in")
    public Result insertBackLog(@RequestBody BackLog backLog){
        int id = backLogService.insertBakcLog(backLog);
        //返回新增记录的id
        return Result.data(id);
    }

    @Operation(summary = "修改待办的状态", description = "输入id和state,修改id记录的state属性")
    @PostMapping("/upstate")
    public Result updateBackLogState(@RequestBody BackLog backLog){
        backLogService.updateStateById(backLog);
        return Result.success();
    }

    @Operation(summary = "修改待办的内容", description = "输入id和text,修改id记录的text属性")
    @PostMapping("/uptext")
    public Result updateBackLogText(@RequestBody BackLog backLog){
        backLogService.updateTextById(backLog);
        return Result.success();
    }

    @Operation(summary = "删除待办", description = "输入id,修改id记录的del属性,达到删除信息的功能")
    @GetMapping("/del/{id}")
    public Result updateBackLogDel(@PathVariable("id") Integer id){
        backLogService.updateDelById(id);
        return Result.success();
    }
}
