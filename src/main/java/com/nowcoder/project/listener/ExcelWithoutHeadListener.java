package com.nowcoder.project.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ConverterUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Create by jiangzh on 2022/10/18 17:29
 * 仿AnalysisEventListener改造
 */
public class ExcelWithoutHeadListener<T> implements ReadListener<T> {
    private List<T> dataList = new ArrayList<>();

    // 读取一行表头
    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        this.invokeHeadMap(ConverterUtils.convertToStringMap(headMap, context), context);
    }

    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        //do nothing
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        throw exception;
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        return true;
    }

    // 读取一行数据
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        this.dataList.add(t);
    }

    // 读取数据结束
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //do nothing
    }

    public List<T> getDataList() {
        return dataList;
    }
}
