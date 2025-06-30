package com.weblearn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {


    private Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender;
    private String image;
    private Short job;
    private LocalDate entrydate;  // LocalDate 是 Java 8 引入的日期类型，表示不带时间的日期
    // 下面这几个在数据库中是命名为 dept_id 的字段，这里规范为驼峰格式
    private Integer deptId;
    private LocalDateTime createTime;  // LocalDateTime 是 Java 8 引入的日期时间类型，表示带时间的日期
    private LocalDateTime updateTime;


}