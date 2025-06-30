package com.weblearn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootdemoApplicationTests {


    @Autowired
    private com.weblearn.service.DeptService deptService; // 注入DeptService服务

    @Test
    void contextLoads() {
    }



    // 现在推荐直接去前后端联调测试
    // 就直接启动那个前端写好的nginx服务器，然后访问前端页面，前端页面会通过axios请求后端接口来获取数据
//    @Test
//    void testDeletebyid() {
//
//        Integer id = 1; // 假设要删除的ID为1
//        // 调用DeptService的deleteById方法
//        deptService.deleteById(id);
//
//    }

}
