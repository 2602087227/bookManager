package com.cjj.bookManager.controller;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.cjj.bookManager.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: CommonController
 * Package: com.cjj.bookManager.controller
 * Description:
 *
 * @Author: 陈
 * @Create: 2023/2/19 - 21:02
 * @Version: v1.0
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${book.path}")
    String basePath;


    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public R<String> upload(MultipartFile file) {

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //拼接成新的文件名

        String fileName = "images/"+UUID.randomUUID().toString() + suffix;
        File dir = new File(basePath);
        //判断文件是否存在,如果不存在就创建一个文件夹
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            file.transferTo(new File(basePath+ fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.success(fileName);
    }
//    public void test(MultipartFile file){
//        String originalFilename = file.getOriginalFilename();
//        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
//        String fileName = UUID.randomUUID().toString()+suffix;
//        File dir = new File(basePath);
//        if (!dir.exists()){
//            dir.mkdir();
//        }
//        try{
//            file.transferTo(new File(basePath+fileName));
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public void test2(String name,HttpServletResponse response){
//        try {
//            FileInputStream fileInputStream =new FileInputStream(new File(basePath +name));
//            ServletOutputStream outputStream = response.getOutputStream();
//            response.setContentType("image/jpeg");
//            int len = 0;
//            byte[] bytes = new byte[1024];
//            while ((len = fileInputStream.read(bytes))!=-1){
//                outputStream.write(bytes,0,len);
//                outputStream.flush();
//            }
//            outputStream.close();
//            fileInputStream.close();
//        }catch (IOException e){
//
//
//        }
//    }
    /**
     * 图片回传
     *
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
