package org.uims.datamaintain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.uims.common.service.SimpleGetListPage;
import org.uims.common.service.SimpleInsertOneService;
import org.uims.datamaintain.user.dto.UserInfoDo;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    @Qualifier(value = "userService")
    private SimpleInsertOneService<UserInfoDo> simpleInsertOneService;
    @Autowired
    @Qualifier(value = "userService")
    private SimpleGetListPage simpleGetListPage;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public int addUser(){
        for(int i=0;i<1000000;i++){
            UserInfoDo userInfoDo = new UserInfoDo();
            userInfoDo.setFullName("测试数据"+i);
            userInfoDo.setAge(30);
            userInfoDo.setJob("Java coder");
            userInfoDo.setSex("2");
            simpleInsertOneService.insertOne(userInfoDo);
        }

        return 10;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<UserInfoDo> getPageList(){
        return simpleGetListPage.getListPage();
    }
}
