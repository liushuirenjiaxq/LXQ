package com.bwl.mapper;

import com.bwl.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectUserId(int id);

    User selectUserLogin(String phone,String password);

    List<User> selectUserAll();

    int insertUser(User user);

    User selectUserTele(String phone);
}
