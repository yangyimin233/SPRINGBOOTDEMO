package com.weblearn.service.impl;


import com.weblearn.mapper.EmpMapper;
import com.weblearn.pojo.Emp;
import com.weblearn.pojo.PageBean;
import com.weblearn.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImplA implements EmpService {


    @Autowired
    private EmpMapper empmapper;


    @Override
    // 分页查询实现
    public PageBean page(Integer page, Integer pageSize) {
//        return null;

        // 1. 获取总记录数
        Long count = empmapper.count();

        // 2. 获取分页查询的结果列表
        // 注意这个start是怎么计算的
        List<Emp> list = empmapper.page((page-1)*pageSize,pageSize);

        // 3. 封装到pagebean里面，并且返回
        return new PageBean(count,list);

        // 其实这里我还是有点疑问，按照黑马程序员那里，比如一开始他设置每页显示10条，假设这时候有10页，他选择第10页，然后给他返回去了，没有问题，前端显示出来了
        // 之后他改了一下每页显示100条，此时可能就只有1条，那他怎么显示1页的情况？
        // 这个可能要去看他前端到底是怎么搞的？比如你重新设置了每页100条后，他是不是就直接触发get请求了(然后就传入pagesize = 100，page = null) ，这样目前看起来是比较合理的

        // 不管什么你怎么设置这个page和pagesize，这个count一定是固定的，这个主要是为了返回给前端
        // 然后前端就看也 根据这个计算一共有多少页，之后你可以选择某一页，又触发一个get请求，把对应页的数据给你返回来

        // 所以我猜测
        // 前端会是这种条件安排
        // 马上给你安排个断点看一下
        // 1. 只要设置了每页查询记录数，则立即触发get请求，同时pagesize 默认null传进来，然后被赋值为1
        // 因为调整这个值会影响page数


        // 2. 只改动选择的page，触发get请求进来时，pagesize不变，还是之前的默认值

        // 我去测了一下发现事情不是这个样子的，很奇怪
        // 首先，前端那里只要改了page或者pagesize都会发起get请求，这个是没有问题的

        // 然后重点来了，pagesize不动时，换page，此时请求传入的是 新page和原来的pagesize，这个没问题，和我想得一样
        // 但是，当page不动，换pagesize时，这个就有点问题了
        // 比如一共17条数据，一开始时pagesize = 5， 有4页，我先选择的时page = 3，第三页
        // 然后我这时候选择pagesize = 10 ，注意这时候只有两页了，此时请求 他传入的page 自动就设置成了2，有点奇怪？ 也就是说前端这里肯定时有一个计算的

        // 那他前端应该时有个计算吧，如果当前页码 超出了 当前pagesize对应的最大页码，则自动将pagesize传入值设置为最后一页。


    }
}
