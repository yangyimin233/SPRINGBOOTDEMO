package com.weblearn.mapper;


import com.weblearn.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {



    // mappper接口中定义的方法，通常是用来执行数据库操作的
    // 想想这里跟service层的接口有什么区别？service层里面会自己去写实现类
    // 而mapper层里面往往写的接口方法会配合注解或者你的xml映射，由mybatis自动生成实现类，并且将其注入到Spring容器中


    /**
     * 查询所有部门
     * @return 部门列表
     */

    // 这里我们就先用注解来定义一个方法，后续可以根据需要去写xml映射文件来实现更复杂的查询
    @Select("SELECT * FROM dept")
    public List<Dept> list();


    // 根据id删除部门信息
    @Delete("DELETE FROM dept WHERE id = #{id}")
    public void deleteById(Integer id);

    /**
     * 增加dept对象的部门
     * @param dept 部门列表
     */
    // 注意这个insert的sql语句，dept中id字段是主键自增的，所以不需要插入，插入另外三个就可以了
    @Insert("INSERT INTO dept(name, create_time, update_time) VALUES(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);


}
