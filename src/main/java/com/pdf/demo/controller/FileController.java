package com.pdf.demo.controller;

import com.pdf.demo.word2pdf.WordToPdf;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

/**
 * @Author huangzhen
 * @create 2020/12/5 13:38
 */
@RestController
@RequestMapping("/file")
public class FileController {


    @RequestMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file, @RequestParam Map map) throws IOException {
        System.out.println(map.get("name"));
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }

        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);

        WordToPdf.doc2pdf(toFile);
        return 1;
    }


    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
