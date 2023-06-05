package com.nowcoder.project.model.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

@HeadRowHeight(30)
public class TaUserImportVO {

    /**
     * 账户编号
     */
    @ExcelIgnore
    private String userId;

    /**
     * 登录账号
     */
    @ExcelProperty("登录账号")
    @ColumnWidth(25)
    private String loginId;

    /**
     * 登录密码
     */
    @ExcelProperty("登录密码")
    @ColumnWidth(20)
    private String password;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    @ColumnWidth(20)
    private String name;

    /**
     * 性别
     */
    @ExcelProperty("性别")
    @ColumnWidth(10)
    private String sex;

    /**
     * 证件类型
     */
    @ExcelIgnore
    private String idCardType;

    /**
     * 证件号码
     */
    @ExcelProperty("证件号码")
    @ColumnWidth(40)
    private String idCardNo;

    /**
     * 手机号码
     */
    @ExcelProperty("手机号码")
    @ColumnWidth(30)
    private String mobile;


    /**
     * 工号
     */
    @ExcelProperty("工号")
    @ColumnWidth(20)
    private String jobNumber;

    /**
     * 学历
     */
    @ExcelProperty("学历")
    private String education;

    /**
     * 邮箱地址
     */
    @ExcelProperty("邮箱地址")
    @ColumnWidth(40)
    private String email;

    /**
     * 联系地址
     */
    @ExcelProperty("联系地址")
    @ColumnWidth(40)
    private String address;

    /**
     * 邮政编码
     */
    @ExcelProperty("邮政编码")
    @ColumnWidth(20)
    private String zipCode;

    /**
     * 工作单位
     */
    @ExcelProperty("工作单位")
    @ColumnWidth(40)
    private String workplace;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
}
