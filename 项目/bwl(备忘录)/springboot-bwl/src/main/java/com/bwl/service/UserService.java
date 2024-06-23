package com.bwl.service;

import com.bwl.mapper.UserMapper;
import com.bwl.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User selectUserId(int id){
        return userMapper.selectUserId(id);
    }

    public User selectUserLogin(String phone,String password){
        return userMapper.selectUserLogin(phone,password);
    }

    public List<User> selectUserAll(){
        return userMapper.selectUserAll();
    }

    public int insertUser(User user){
        User temp = userMapper.selectUserTele(user.getTele());
        if(temp != null){
            return 0;
        }
        userMapper.insertUser(user);
        return 1;
    }
}
