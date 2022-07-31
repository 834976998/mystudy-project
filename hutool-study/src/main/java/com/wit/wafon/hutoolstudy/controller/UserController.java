package com.wit.wafon.hutoolstudy.controller;

import com.wit.wafon.hutoolstudy.entitys.vo.QueryUserParamVo;
import com.wit.wafon.hutoolstudy.service.DmDbService;
import com.wit.wafon.hutoolstudy.service.TbUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.SQLException;

/**
 * @author fengwang26
 * @date 2022/7/4 22:04
 * @describe
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "查询用户信息",tags = "用户信息")
public class UserController {

    @Autowired
    private TbUserService userService;

    @Autowired
    private DmDbService dmDbService;

    @PostMapping("/getById")
    @ApiOperation(value = "查询用户信息")
    public String getUser(
            @RequestBody
            @ApiParam(value = "请求入参",required = true) QueryUserParamVo userParamVo
    ) throws SQLException {
        userService.getUser(userParamVo);
        return "查询完成";
    }

    @GetMapping("/getBySql")
    @ApiOperation(value = "Sql拼接")
    public String getBySql(
    ) throws SQLException {
        userService.getBySql();
        return "查询完成";
    }

    @PostMapping("/insertForGeneratedKey")
    @ApiOperation(value = "新增")
    public Long insertForGeneratedKey(String name,String pwd) throws SQLException {
        return userService.insertForGeneratedKey(name,pwd);
    }

    @ApiOperation(value = "通过名称删除")
    @DeleteMapping(value = "/deleteByName")
    public int deleteByName(String name) throws SQLException {
        return userService.deleteByName(name);
    }

    @ApiOperation(value = "达梦数据库测试")
    @GetMapping(value = "/getDmUser")
    public boolean getDmAllUser() throws SQLException {
        dmDbService.getAllUser();
        return true;
    }




}
