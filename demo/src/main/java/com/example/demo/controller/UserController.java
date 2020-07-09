package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.utils.style.JSONResult;
import com.example.demo.service.UserService;
import com.example.demo.utils.StatusUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "用户")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "登录")
    @PostMapping("login")
    public JSONResult login(@RequestParam String username,@RequestParam String password){
        return userService.login(username,password);
    }

    @ApiOperation(value="获取我的用户数据")
    @PostMapping("getUser")
    public JSONResult getUser(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        if(claims==null){
            return new JSONResult(StatusUtil.NO_LOGIN,"未登录");
        }
        Long id=Long.parseLong(claims.getId());
        return new JSONResult(StatusUtil.OK,userService.getUser(id),"查询成功");
    }

    @ApiOperation(value="修改用户数据")
    @PostMapping("updateUser")
    public JSONResult updateUser(HttpServletRequest request, @RequestBody User user){
        Claims claims = (Claims) request.getAttribute("claims");
        if(claims==null){
            return new JSONResult(StatusUtil.NO_LOGIN,"未登录");
        }
        Long id=Long.parseLong(claims.getId());
        user.setId(id);
        userService.updateUser(user);
        return new JSONResult(StatusUtil.OK,"修改成功");
    }

    @ApiOperation(value ="根据名称分页查询用户数据")
    @GetMapping("queryUserListByName")
    public JSONResult queryUserList(@RequestParam int pageNo,@RequestParam int pageSize, @RequestParam(defaultValue = "") String name,HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        if(claims==null){
            return new JSONResult(StatusUtil.NO_LOGIN,"未登录");
        }
        return new JSONResult(StatusUtil.OK,userService.queryUserList(pageNo,pageSize,name),"查询成功");
    }
}
