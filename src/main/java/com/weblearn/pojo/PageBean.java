package com.weblearn.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    // 这个是用来封装分页查询的结果的类

    private Long total; // 总记录数
    private List<Emp> rows;   // 当前页的记录列表
    // 注意他这个 rows 表示 当前页的记录列表，而不是全部的记录列表，是后端根据前端传进来的分页参数查询出来的当前页的记录列表

}
