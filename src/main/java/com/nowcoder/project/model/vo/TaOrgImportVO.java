package com.nowcoder.project.model.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

@HeadRowHeight(30)
public class TaOrgImportVO {

    /**
     * 组织ID
     */
    @ExcelIgnore
    private String orgId;

    /**
     * 组织路径
     */
    @ExcelProperty("组织路径")
    @ColumnWidth(40)
    private String namePath;

    /**
     * 组织类型
     */
    @ExcelProperty("组织类型")
    @ColumnWidth(12)
    private String orgType;

    /**
     * 行政区划编码
     */
    @ExcelProperty("行政区划编码")
    @ColumnWidth(30)
    private String area;

    /**
     * 自定义编码
     */
    @ExcelProperty("自定义编码")
    @ColumnWidth(30)
    private String customNo;
;

    /**
     * 组织代码
     */
    @ExcelProperty("组织代码")
    @ColumnWidth(30)
    private String orgCode;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getNamePath() {
        return namePath;
    }

    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCustomNo() {
        return customNo;
    }

    public void setCustomNo(String customNo) {
        this.customNo = customNo;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
