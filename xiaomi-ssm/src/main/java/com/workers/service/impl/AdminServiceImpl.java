package com.workers.service.impl;

import com.workers.pojo.Admin;
import com.workers.pojo.AdminExample;
import com.workers.mapper.AdminMapper;
import com.workers.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    //在业务逻辑层中，一定会有数据访问层的对象
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {
        //根据传入的用户或到DB中查询相应用户对象
        //如果有条件，则一定要创建AdminExample的对象，用来封装条件
        AdminExample example = new AdminExample();
        /**如何添加条件
         * select * from admin where a_name = 'cjj'
         */
        //添加用户名a_name条件 以下这个就是得到a_name = 'cjj'这个条件
        example.createCriteria().andANameEqualTo(name);

        List<Admin> list = adminMapper.selectByExample(example);
        if(list != null&& list.size()>0){
            Admin admin = list.get(0);
            //如果查询到用户对象，再进行密码的比对
            if(pwd.equals(admin.getaPass())){
               return admin;
            }
        }
        return null;
    }
}
