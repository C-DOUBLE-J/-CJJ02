package com.workers.controller;

import com.workers.pojo.Admin;
import com.workers.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//管理员账号直接添加到数据库，不开放注册接口
@Controller
@RequestMapping("/admin")
public class AdminAction {
    //在所有的界面层，一定会有业务逻辑层的对象
    @Autowired
    AdminService adminService;
    //实现登录判断，并进行相应的跳转
    @RequestMapping("/login")
    public String login(String name, String pwd, Model model){
        Admin admin = adminService.login(name, pwd);
        if (admin !=null){
            //登陆成功
            model.addAttribute("admin",admin);
            return "main";
        }else{
            //登录失败
            model.addAttribute("errmsg", "登录失败");
            return "login";
        }

    }
}
