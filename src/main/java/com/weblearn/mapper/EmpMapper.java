package com.weblearn.mapper;


import com.weblearn.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    这里需要定义两个方法，一个是查询员工信息的分页方法，另一个是查询员工总数的方法

    // 分页查询员工信息
    @Select("SELECT * FROM emp LIMIT #{offset}, #{limit}")
    List<Emp> page(@Param("offset") Integer start, @Param("limit") Integer pageSize);
    // 这个@Param注解表示将改入口参数映射到上面select注解对应的sql语句里面的参数去
    // 比如这里就是将入口参数start映射到那个sql语句的offset里面

    // 查询员工总数
    @Select("SELECT COUNT(*) FROM emp")
    Long count(); // 这里还是跟PageBean对应起来比较好


    // 这个方法给那个测试pagehelper的方法用一下，pagehelper插件只需要调用全部查询的mapper接口就可以

    // 因为现在我们是引入条件的动态sql，所以这里还是需要引入xml文件来做动态sql方便一些，就不用下面这种注解的简单方式了
//    @Select("SELECT * FROM emp")
// 这种mappper文件的配置方法参考之前那个mybatis项目
   // 在spring项目里面 的 那个 MyBatisDemo模块


    List<Emp> mapperForPagehelper(String name,
                                  Short gender,
                                  LocalDate begin,
                                  LocalDate end);




}
