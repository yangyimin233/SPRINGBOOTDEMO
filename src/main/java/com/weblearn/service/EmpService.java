package com.weblearn.service;

import com.weblearn.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface EmpService {


    // page接口规范
    PageBean page(Integer page, Integer pageSize,
                  String name,
                  Short gender,
                  LocalDate begin,
                  LocalDate end);
}
