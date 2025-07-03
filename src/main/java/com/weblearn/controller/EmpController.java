package com.weblearn.controller;


import com.weblearn.pojo.PageBean;
import com.weblearn.pojo.Result;
import com.weblearn.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@Slf4j   // 这个注解打上后，调用里面的log比较方便
@RestController
public class EmpController {



    // 这里可以注入一个EmpService服务接口，后面会有实现类来实现这个接口
    // Qualifier 搭配autowried使用 指定要注入哪个类
    // 在对应的实现类中，我们会采用@SERVICE（）后面属性 指定 标签，没加属性，默认标签为类名小写
    @Qualifier("empServiceImplB") // 指定注入 EmpServiceImplB 实现类
    @Autowired

    private EmpService empService;

    // 分页查询员工信息
    @GetMapping("/emps")
    // 注意这个入口参数哦，跟之前写的一样，只要写得跟get请求里面一样，就直接传进来了


    // 现在这里我们来改一下，需要分页查询上面再加上条件查询
    // 所以这里前端传进来的请求参数还有查询条件
    // 然后这里controller层调用service层的时候再把这些条件参数也查询传进去
    // 同理，service层再加这个参数，调用mapper层就可以了
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5")  Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        // 这个DateTimeFormat注解是用来匹配前端传进来的时间的


        // 这是参数的默认设置，当然可以用@RequestParam里面的属性来设置
//        if (page == null) {
//            page = 1;
//        }
//        if (pageSize == null) {
//            pageSize = 10;
//        }

        log.info("分页查询，参数：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
//        System.out.println("页码");
//        System.out.println(page);
//        // 这里可以调用service层的业务逻辑来查询员工信息
//        // 目前先返回一个成功的响应，后面再完善
//        return Result.success("分页查询员工信息成功");

        // 调用service层的接口来返回得到那个pagebean对象(每页的数据列表)，然后给他封装到result里面 ，再返回回去
//        PageBean pageBean = empService.page(page,pageSize);
        // 增加查询参数
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);

    }
    // 这里补充一下 框架约束：Spring 的注解（如 @GetMapping、@PostMapping 等）要求方法必须是 public，否则不会被注册为处理器，因此，REST API 的方法必须保持 public 访问级别。




}
