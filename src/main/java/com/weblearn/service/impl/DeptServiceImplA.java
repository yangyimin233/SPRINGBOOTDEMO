package com.weblearn.service.impl;


import com.weblearn.mapper.DeptMapper;
import com.weblearn.pojo.Dept;
import com.weblearn.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImplA implements DeptService {


    // 同理按照三层架构，这个service层肯定也需要调用DAO层的业务逻辑，所以我们需要注入一个DAO对象
    @Autowired
    private DeptMapper deptMapper;



    @Override
    public List<Dept> list() {
        // 实现查询全部部门数据
        return deptMapper.list(); // 这里调用了DAO层的list方法，返回全部部门数据
    }

    @Override
    public void deleteById(Integer id) {
        // 删除部门信息，根据id删除部门信息
        deptMapper.deleteById(id); // 这里调用了DAO层的deleteById方法，根据id删除部门信息
    }

    @Override
    public void add(Dept dept) {

        // 这里service层还需要补全dept的一些属性，比如创建时间和更新时间，然后再调用DAO层的insert方法来新增部门信息

        // 获取当前时间戳
        LocalDateTime now = LocalDateTime.now();
        // 设置创建时间和更新时间
        dept.setCreateTime(now);
        dept.setUpdateTime(now);
        // 调用DAO层的insert方法，新增部门信息
        deptMapper.insert(dept); // 这里调用了DAO层的insert方法，新增部门信息

    }

}
