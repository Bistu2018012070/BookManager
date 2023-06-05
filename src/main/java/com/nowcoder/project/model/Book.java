package com.nowcoder.project.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

/**图书实体类
 * @author zhangzq
 * @date 2023/5/6 9:35
 */
@HeadRowHeight(20)
public class Book {

  /**
   * 图书ID
   */
  @ExcelIgnore
  private int id;

  /**
   * 书名
   */
  @ExcelProperty("书名")
  @ColumnWidth(30)
  private String name;

  /**
   * 作者
   */
  @ExcelProperty("作者")
  private String author;

  /**
   * 价格
   */
  @ExcelProperty("价格")
  private String price;

  /**
   * 状态
   * {@link com.nowcoder.project.model.enums.BookStatusEnum}
   */
  @ExcelProperty("是否借出（1是 0否）")
  @ColumnWidth(30)
  private int status;

  /**
   * 导入失败原因
   */
  @ExcelIgnore
  private String failReason;

  public String getFailReason() {
    return failReason;
  }

  public void setFailReason(String failReason) {
    this.failReason = failReason;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
