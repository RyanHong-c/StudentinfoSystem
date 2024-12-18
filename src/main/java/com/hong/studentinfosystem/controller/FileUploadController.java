package com.hong.studentinfosystem.controller;

import com.hong.studentinfosystem.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("")
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        return null;
    }
}
