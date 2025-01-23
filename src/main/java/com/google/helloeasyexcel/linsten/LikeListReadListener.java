package com.google.helloeasyexcel.linsten;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Cell;
import com.alibaba.excel.metadata.data.CellData;
import com.google.helloeasyexcel.model.FailedLikelist;
import com.google.helloeasyexcel.model.LikeList;
import com.google.helloeasyexcel.service.LikeListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Component
public class LikeListReadListener extends AnalysisEventListener<LikeList> {

    private LikeListService likeListService;

    private List<LikeList> successData = new ArrayList<>();

    private List<FailedLikelist> failedLikelists = new ArrayList<>();

    public LikeListReadListener(LikeListService likeListService) {
        this.likeListService = likeListService;
    }

    public LikeListReadListener() {
    }


    @Override
    public void invoke(LikeList likeList, AnalysisContext analysisContext) {
        try {
            System.out.println(likeList);
            //读取到每一条数据时进入此方法
            successData.add(likeList);
        } catch (Exception e) {
            System.out.println("解析失败:" + likeList.toString());
            log.error("解析失败，但是继续解析下一行:{}", e.getMessage());
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //读取完所有数据后进入此方法
        log.info("excel文件解析完成，成功:{}条，失败:{}条", successData.size(), failedLikelists.size());
        log.warn("解析失败的数据:{}", failedLikelists);
        writeExcel(failedLikelists);
        //todo 上传失败excel到文件服务器，发送mq消息，成功x条，失败y条，失败数据的下载链接
    }

    private void writeExcel(List<FailedLikelist> failedLikelists) {
        //获取系统桌面路径
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        String fileName = desktopPath + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, FailedLikelist.class).sheet("解析失败的数据").doWrite(failedLikelists);
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        FailedLikelist failedLikelist = new FailedLikelist();
        Map<Integer, Cell> cellMap = context.readRowHolder().getCellMap();
        for (Map.Entry<Integer, Cell> cellEntry : cellMap.entrySet()) {
            CellData cell = (CellData) cellEntry.getValue();
            String columnData = "";
            switch (cell.getType()) {
                case STRING:
                    columnData = cell.getStringValue();
                    break;
                case NUMBER:
                    columnData = cell.getNumberValue().toString();
                    break;
                case BOOLEAN:
                    columnData = cell.getBooleanValue().toString();
                    break;
                default:
                    break;
            }
            switch (cell.getColumnIndex()) {
                case 0:
                    failedLikelist.setId(columnData);
                    break;
                case 1:
                    failedLikelist.setProductName(columnData);
                    break;
                case 2:
                    failedLikelist.setPrice(columnData);
                    break;
                case 3:
                    failedLikelist.setPurchasingDate(columnData);
                    break;
                default:
                    break;
            }
        }
        failedLikelists.add(failedLikelist);
    }

}

