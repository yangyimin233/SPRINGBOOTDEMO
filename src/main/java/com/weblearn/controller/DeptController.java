package com.weblearn.controller;


import com.weblearn.pojo.Dept;
import com.weblearn.pojo.Result;

import com.weblearn.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {


//    private static Logger log = LoggerFactory.getLogger(DeptController.class);   // 可以直接加个，Slf4j注解，就不用每个类里面都new一个这个日志对象了



//    @RequestMapping(value = "/depts",method = RequestMethod.GET) // 这个跟的是请求路径，之后前端会通过这个路径来访问这个方法
//    // 上面那个注解的method属性指定了这个方法是处理GET请求的，value属性指定了请求的路径


    // 根据那个三层架构的原理，我们这里的controller层 肯定需要调用 SERVICE层的业务逻辑，所以我们需要注入一个service对象
    @Autowired
    private DeptService deptService; // 这个是我们之前定义的service接口，后面会有实现类来实现这个接口


    @GetMapping("/depts") // 这个是简化版的注解，等同于上面的@RequestMapping(value = "/depts",method = RequestMethod.GET)
    public Result list(){

        log.info("查询全部的部门信息"); // 这个是日志打印，info级别的日志，打印查询全部部门信息的提示

        // 这里我们需要调用service层的业务逻辑来查询全部的部门信息
        // 到时候这里就用个 deptService.list()来实现就行了
        List<Dept> deptList = deptService.list();



        return Result.success(deptList); // 这个是那个result里面的一个静态方法，返回一个成功的响应

    }


    /*
    * 删除部门信息
    * */
    @DeleteMapping("/depts/{id}") // 这个是删除部门信息的请求路径，{id}是占位符，表示要删除的部门id
    public Result deleteById(@PathVariable Integer id) {

        log.info("删除部门信息，id为：{}", id); // 这个是日志打印，info级别的日志，打印删除部门信息的提示

        // 这里我们需要调用service层的业务逻辑来删除部门信息
        deptService.deleteById(id);

        return Result.success(); // 这个是那个result里面的一个静态方法，返回一个成功的响应，因为是删除操作，所以不需要给页面返回数据
    }

    /*
     * 新增部门信息
     * */
    @PostMapping("/depts") // 这个是新增部门信息的请求路径
    // 注意这个页面传进来的请求参数，他是一个JSON格式的数据，所以我们需要用@RequestBody注解来接收这个数据
    public Result add(@RequestBody Dept dept) { // @RequestBody注解表示请求体中的数据会被转换成Dept对象
        // 之后可以看看这个@RequestBody注解的用法，或者直接去看前端的代码，前端会通过axios发送一个POST请求到这个路径，并且携带一个JSON格式的数据
        // 前端过来一个post请求，请求参数在请求体中，json格式 {”name": "部门名称"}
        // 这里断点看了一下： Dept对象里面的属性值是正确的，且只有name属性被赋值了，id为null，createTime和updateTime属性是null的，因为前端没有传这两个属性，所以需要在service层补全这两个属性
        log.info("新增部门信息，部门名称为：{}", dept); // 这个是日志打印，info级别的日志，打印新增部门信息的提示
        // 这里我们需要调用service层的业务逻辑来新增部门信息
        deptService.add(dept);
        return Result.success(); // 这个是那个result里面的一个静态方法，返回一个成功的响应，因为是新增操作，所以不需要给页面返回数据

    }




}
