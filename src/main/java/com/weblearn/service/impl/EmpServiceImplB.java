package com.weblearn.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.weblearn.mapper.EmpMapper;
import com.weblearn.pojo.Emp;
import com.weblearn.pojo.PageBean;
import com.weblearn.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
// 注意，这里顺便说一下这个容器的多重选择
// 比如这里我有两个实现类均实现了EmpService，现在都给他加上SERVICE注解
// 此时注解value要跟上标签
// 然后在上层(controller)中 使用@autowriede注解下面再加上
// @Qualifier("empServiceImplB") 就可以指定要注入的实现类了

public class EmpServiceImplB implements EmpService {



    // 这里补充一下

//    在 Spring Boot 中，@Service 注解的作用是将类标记为一个服务组件，并将其注册到 Spring 容器中。只有被 Spring 容器管理的 Bean 才能使用 @Autowired 自动注入依赖。
//    如果不注解 @Service，EmpServiceImplB 类不会被 Spring 容器识别和管理，因此 @Autowired 无法正常工作，因为 Spring 无法为该类注入依赖（如 EmpMapper）
//    换句话说，@Autowired 依赖于该类本身是一个 Spring 管理的 Bean，而 @Service 是让 Spring 管理该类的关键注解。

    @Autowired
    private EmpMapper empMapper;




    @Override
    // 这是采用pagehelper插件去做这个分页查询的实现
    public PageBean page(Integer page, Integer pageSize,
                         String name,
                         Short gender,
                         LocalDate begin,
                         LocalDate end) {


        // 1. 设置分页参数 ,直接调用插件里面的这个方法
        PageHelper.startPage(page, pageSize);

        // 2. 执行查询 , 还是调用mapper层的接口
        List<Emp> empList = empMapper.mapperForPagehelper(name, gender, begin, end);
        // 然后在把类型转化一下(mapper层返回的是list，这里插件里面自己带来强转的Page类型)
        Page<Emp> p = (Page<Emp>) empList;


        // 3. 封装成pagebean返回给controller层
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return  pageBean;

//        // 1. 设置分页参数 ,直接调用插件里面的这个方法
//        PageHelper.startPage(page, pageSize);
//
//        // 2. 执行查询 , 还是调用mapper层的接口
//        List<Emp> empList = empMapper.mapperForPagehelper();
//
//        // 3. 封装成pagebean返回给controller层
//        PageBean pageBean = new PageBean(((com.github.pagehelper.Page<Emp>) empList).getTotal(), empList);
//        return pageBean;


        // 但说实话这个插件感觉没啥用？



//        return null;


        // 看看本地commit一下的呢/


    }

    @Override
    public void deleteEmp(List<Integer> ids) {

        // 这里没啥逻辑，直接掉mapper层接口方法就行了
        empMapper.mapperForDelete(ids);

    }

    @Override
    public void addEmp(Emp emp) {

        // 这里跟之前新建部门一样,需要补全一些信息在这里,比如cratetime,再传入mapper层
        // 获取当前时间戳
        LocalDateTime now = LocalDateTime.now();
        // 设置创建时间和更新时间
        emp.setCreateTime(now);
        emp.setUpdateTime(now);

        // 然后再调用emp方法
        empMapper.mapperForAdd(emp);



    }
}
