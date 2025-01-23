package com.google.helloeasyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.google.helloeasyexcel.linsten.LikeListReadListener;
import com.google.helloeasyexcel.model.LikeList;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/likeList")
public class LikeListController {

    @PostMapping("/uploadAndParseExcel")
    public String uploadAndParseExcel(@RequestParam MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), LikeList.class, new LikeListReadListener()).sheet().doRead();
        } catch (IOException e) {
            return "Error occurred while reading the file: " + e.getMessage();
        }
        return "upload success";
    }
}
