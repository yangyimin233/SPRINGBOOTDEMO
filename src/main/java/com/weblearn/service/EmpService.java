package com.weblearn.service;

import com.weblearn.pojo.Emp;
import com.weblearn.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {


    // page接口规范
    PageBean page(Integer page, Integer pageSize,
                  String name,
                  Short gender,
                  LocalDate begin,
                  LocalDate end);



    // 这里再定义一个删除员工的操作
    void deleteEmp(List<Integer> id);

    // 新增员工
    void addEmp(Emp emp);


}





