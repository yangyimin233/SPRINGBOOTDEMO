package com.weblearn.controller;

import com.weblearn.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Slf4j

@RestController
public class UploadController {


    // 这个controller用来是演示文件上传操作


    // 其实大差不差，这个对应 那个表单的 请求路径为 /upload


//    <form action="/upload" method="post" enctype="multipart/form-data">
//    姓名: <input type="text" name="username"><br>
//    年龄: <input type="text" name="age"><br>
//    头像: <input type="file" name="image"><br>
//        <input type="submit" value="提交">
//    </form>


    @PostMapping("/upload")

    // 入口参数还是跟之前是一样的，一般来说就保持跟表单项的name字段保持一致就行，对于文件类型，可以直接使用SRPING提供的api
    // 如果你想弄成不一致的，就使用那个注解
    // 比如下面这个image 就可以换成  @RequestParam("image") MultipartFile file

    public Result upload(String username, Integer age, MultipartFile image) throws Exception {

        log.info("文件上传：{} ，{}， {}",username,age,image);
        // 将接收到的文件存储到服务器的指定磁盘中
        // 不然 测试了一下，这个是直接扔到C盘了，程序一结束就自动清理掉了
        // 先获取原始文件名
        String name = image.getOriginalFilename();
        // 这个路径结尾也要记得加\\
        image.transferTo(new File("C:\\Users\\Administrator\\Desktop\\"+name));
        // 然后这里试了一下，这边确实没问题，能直接把上传的图片存到桌面上，之后这里也要处理 文件名重复的问题:两个用户上传相同名字的 图片，会出现覆盖的问题
        // 解决办法：采用uuid
        return Result.success();


        // 这个样子 是 将文件 存在本机服务端， 之后 访问 图片就访问本机对应的这个URL
        // 但更为主流的做法是将 图片存到 诸如 阿里云之类的云服务上面，
        // 然后本机服务端，就存对应的URL就行，比如数据库里面的那个imgae存的就是各种URL




    }





}
