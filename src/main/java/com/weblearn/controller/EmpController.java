package com.weblearn.controller;


import com.weblearn.pojo.Emp;
import com.weblearn.pojo.PageBean;
import com.weblearn.pojo.Result;
import com.weblearn.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


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



    // 开始做删除员工的操作

    @DeleteMapping("/emps/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("执行批量删除操作，ids：{}",ids);

        empService.deleteEmp(ids);

        return Result.success();

    }

    // 然后是新增员工
    // 注意这个请求路径 跟 前面那个分页 查询的路径是一样的,但是请求方法不同,一个是POST,一个是GET

//    如果两个方法使用相同的请求路径（如/emps），但请求方法不同（如一个是@GetMapping，另一个是@PostMapping），是没有问题的。Spring Boot 会根据 HTTP 请求方法区分这两个处理器。
//
//    但是，如果两个方法的请求路径和请求方法都相同（例如两个都是@PostMapping("/emps")），会导致冲突，
//    Spring Boot 无法确定应该调用哪个方法。这会抛出一个异常，通常是IllegalStateException，提示映射路径重复。
//
//    建议确保每个请求路径和方法的组合是唯一的。如果需要区分，可以修改路径或添加额外的参数来区分请求

    // 这里再回顾补充一下请求响应流程

//    前端触发请求：
//    前端某个操作（如点击按钮）会发送 HTTP 请求。
//    请求包含请求路径（如 /emps）和请求方法（如 POST）。

//    后端处理请求：
//    后端通过 Spring Boot 的注解（如 @PostMapping("/emps")）定义处理方法。
//    请求路径和方法共同决定调用哪个后端方法。

//    请求路径的作用：
//    请求路径是资源的标识符，用于区分不同的操作。
//    后端根据路径和方法设计对应的业务逻辑。

//    返回响应：
//    后端处理完成后，返回响应数据给前端。
//    前端根据响应更新页面或执行后续操作。

    @PostMapping("/emps")
    public Result add(@RequestBody Emp emp){
        log.info("新增员工, emp: {}", emp);

        empService.addEmp(emp);

        return Result.success();
    }



}
