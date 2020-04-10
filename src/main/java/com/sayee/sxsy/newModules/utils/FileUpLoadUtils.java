package com.sayee.sxsy.newModules.utils;

import com.sayee.sxsy.common.utils.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUpLoadUtils {


    public ResponsesUtils ysjUpLoad(MultipartFile file){
        // 获取文件名称
        String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName)) {
            return ResponsesUtils.build(500,"文件不能为空");
        }
        // 获取文件的大小
        long fileSize = file.getSize();
        if (fileSize > 10 * 1024 * 1024) {
            return ResponsesUtils.build(500,"上传文件大小超出限定大小10M");
        }
        // 获取文件的扩展名
        // String extension = FilenameUtils.getExtension(fileName);
        // 获取配置路径
        String path = "/date/images/";
        String newPath = path + IdGen.uuid().replaceAll("-", "") + "\\";
        File newDir = new File(newPath);
        if (!newDir.exists()) {
            newDir.mkdirs(); // 目录不存在的情况下，创建目录
        }
        File newFile = new File(newDir, fileName);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  ResponsesUtils.ok(newPath+fileName);
    }
}
