package com.weblearn.service;

import com.weblearn.pojo.PageBean;

public interface EmpService {


    // page接口规范
    PageBean page(Integer page, Integer pageSize);
}
