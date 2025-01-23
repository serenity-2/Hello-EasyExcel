package com.google.helloeasyexcel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LikeList {

    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = "商品名称")
    private String productName;

    @ExcelProperty(value = "价格")
    private BigDecimal price;

    @ExcelProperty(value = "购买时间")
    private LocalDate purchasingDate;

}
