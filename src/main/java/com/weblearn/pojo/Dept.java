package com.weblearn.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    private Integer id;          // 部门ID
    private String name;         // 部门名称
    private LocalDateTime createTime;  // LocalDateTime 是 Java 8 引入的日期时间类型，表示带时间的日期
    private LocalDateTime updateTime;

}
