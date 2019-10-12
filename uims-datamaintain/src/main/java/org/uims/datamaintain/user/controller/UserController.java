package org.uims.datamaintain.user.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.uims.datamaintain.user.dto.UserInfoDto;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "",method = RequestMethod.POST)
    public int addUser(@RequestBody UserInfoDto userInfoDto){

        System.out.println("'userInfoDto' = " + userInfoDto);

        return 0;
    }
}
