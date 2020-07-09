package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.exception.CommonException;
import com.example.demo.pojo.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.style.JSONResult;
import com.example.demo.utils.jwt.JWTUtils;
import com.example.demo.utils.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public JSONResult login(String username, String password) {
        if(!userRepository.existsByUsernameAndPassword(username,password)) {
            return new JSONResult(400,null,"账号或者密码错误");
        }
        User user=userRepository.findByUsernameAndPassword(username,password);
        String token=jwtUtils.createJWT(user.getId().toString(),"login", null);
        return new JSONResult(200,JSON.parse("{token:\""+ token +"\"}"),"登录成功");
    }

    /**
     * 修改数据
     * @param user
     */
    @Transactional
    public void updateUser( User user) {
        if(!userRepository.existsById(user.getId())){
            throw new CommonException(StatusUtil.PARAM_ERROR,"用户不存在");
        }
        int count=userRepository.updateUser(user.getId(),user.getCphone(),user.getCunitname());
        if(count<=0){
            throw new CommonException(StatusUtil.ERROR,"未修改成功");
        }
    }

    /**
     * 查询数据
     * @param id
     * @return
     */
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public Page<User> queryUserList(int pageNo, int pageSize,String name) {
        Pageable pageable=PageRequest.of(pageNo-1,pageSize);
        System.out.println(name);
        return userRepository.findByCunitnameLike("%"+name+"%",pageable);
    }
}
