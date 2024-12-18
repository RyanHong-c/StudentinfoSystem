package com.hong.studentinfosystem.service.impl;

import com.hong.studentinfosystem.mapper.UserMapper;
import com.hong.studentinfosystem.pojo.User;
import com.hong.studentinfosystem.service.UserService;
import com.hong.studentinfosystem.utils.Md5Util;
import com.hong.studentinfosystem.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {

        User u = userMapper.findByUserName(username);

        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密MD5
        String md5String = Md5Util.getMD5String(password);

        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
