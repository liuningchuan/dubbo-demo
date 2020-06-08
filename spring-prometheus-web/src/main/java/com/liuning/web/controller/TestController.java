package com.liuning.web.controller;

import com.liuning.common.enums.ErrorCode;
import com.liuning.dao.entity.User;
import com.liuning.dao.entity.UserExample;
import com.liuning.dao.mapper.UserMapper;
import com.liuning.web.aspect.BusiLog;
import com.liuning.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
@Api(tags = "Base Controller", value = "测试接口")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    UserMapper userMapper;

    @GetMapping("/world/{start}/{end}")
    @ApiOperation(value = "2323", notes = "2333")
    @BusiLog(name = "测试接口",ignore = true)
    public List<User> getUser(@PathVariable int start, @PathVariable int end, @RequestParam(required = false) String name) {

        Assert.notNull(name, "URI is required");

        log.info("start is {}, end is {}", start, end);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdBetween(start, end);
        return userMapper.selectByExample(userExample);
    }

    @GetMapping("/result")
    @ApiOperation(value = "Result测试", notes = "返回成功")
    public Result result(){
        return new Result(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage());
    }

    @PostMapping("/result")
    @ApiOperation(value = "Result测试", notes = "返回数据")
    @BusiLog(name = "测试接口",ignore = true)
    public Result<User> result1(@RequestBody User user){
        return new Result<>("0000000", "请求成功", user);
    }

    /**
     * 直接返回字符串
     * @return string
     */
    @GetMapping("/string")
    public String getString() {
        return "SUCCESS";
    }

}