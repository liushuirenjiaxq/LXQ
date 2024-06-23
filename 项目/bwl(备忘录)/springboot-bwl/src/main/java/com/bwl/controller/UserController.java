package com.bwl.controller;

import com.bwl.pojo.Result;
import com.bwl.pojo.User;
import com.bwl.service.UserService;
import com.bwl.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户管理接口")
@CrossOrigin(origins ="*" ,maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Operation(summary = "用户查询功能 id", description = "查询某id用户信息")
    @GetMapping("/{id}")
    public Result selectUserId(@PathVariable("id") Integer id){
        User user = userService.selectUserId(id);
        return Result.data(user);
    }

    @Operation(summary = "用户查询功能 all", description = "查询所有用户信息")
    @GetMapping("/all")
    public Result selectUserAll(){
        List<User> list = userService.selectUserAll();
        return Result.data(list);
    }

    @Operation(summary = "登录功能", description = "输入电话号码和密码，即可登录系统")
    @PostMapping("/login")
    public Result loginUser(@RequestParam("Phone")String phone,@RequestParam("userPwd")String password){
        User user = userService.selectUserLogin(phone,password);
        String token = TokenUtils.createToken(user);
        Result result = Result.data(user);
        result.setMsg(token);
        //登录功能中返回的msg是token（临时用一下）
        return result;
    }

    @Operation(summary = "注册功能", description = "输入姓名、电话号码和密码，即可注册系统")
    @PostMapping("/in")
    public Result insertUser(@RequestBody User user){
        /* 还需要对错误原因，找出并发送到前端 */
        int flag = userService.insertUser(user);
        if(flag == 1){
            return Result.success();
        }else {
            return Result.error("号码已被使用");
        }
    }
}
