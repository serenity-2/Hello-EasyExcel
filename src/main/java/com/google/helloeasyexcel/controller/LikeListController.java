package com.google.helloeasyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.google.helloeasyexcel.linsten.LikeListReadListener;
import com.google.helloeasyexcel.model.LikeList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/likeList")
public class LikeListController {

    @PostMapping("/uploadAndParseExcel")
    public String uploadAndParseExcel(@RequestParam MultipartFile file) {
        CompletableFuture.runAsync(() -> {
            try {
                EasyExcel.read(file.getInputStream(), LikeList.class, new LikeListReadListener()).sheet().doRead();
            } catch (IOException e) {
                log.error("Error occurred while reading the file: " + e.getMessage());
            }
             log.info("excel文件解析完成");
        });
        return "文件上传中，请在站内信查看结果";

    }
}
