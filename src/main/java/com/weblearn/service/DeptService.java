package com.weblearn.service;

import com.weblearn.pojo.Dept;

import java.util.List;



// 跟之前一样，我们这里先写成一个接口，后续可以根据需要去写不同的实现类来实现不同的版本
public interface DeptService {


    /**
     * 查询所有部门
     * @return 部门列表
     */
    List<Dept> list();


    /**
     * 根据id删除部门信息
     * @param id 部门ID
     */
    void deleteById(Integer id); // 删除部门信息，根据id删除部门信息

    /**
     * 新增部门信息
     * @param dept 部门对象
     */
    void add(Dept dept); // 新增部门信息，根据部门名称新增部门信息

}
