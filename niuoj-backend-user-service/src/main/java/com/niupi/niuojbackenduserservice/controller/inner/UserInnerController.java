package com.niupi.niuojbackenduserservice.controller.inner;

import com.niupi.niuojbackendmodel.model.entity.User;
import com.niupi.niuojbackendserviceclient.service.UserFeignClient;
import com.niupi.niuojbackenduserservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 内部服务调用，不暴露给前端调用
 */
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {
    @Resource
    private UserService userService;
    /**
     * 根据id获取用户
     *
     * @param userId
     * @return
     */
    @Override
    @GetMapping("/get/id")
    public User getById(@RequestParam("userId") long userId){
        return userService.getById(userId);
    }

    /**
     * 根据id获取用户列表
     *
     * @param idList
     * @return
     */
    @Override
    @GetMapping("get/ids")
    public List<User> listByIds(@RequestParam("idList") Collection<Long> idList){
        return userService.listByIds(idList);
    }
}
