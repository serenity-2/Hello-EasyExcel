package com.google.helloeasyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;


@HeadRowHeight(30)
@ContentRowHeight(30)
@ColumnWidth(30)
@Data
public class FailedLikelist {

    @ExcelProperty(value = "商品编号")
    private String id;

    @ExcelProperty(value = "商品名称")
    private String productName;

    @ExcelProperty(value = "价格")
    private String price;

    @ExcelProperty(value = "购买时间")
    private String purchasingDate;
}
