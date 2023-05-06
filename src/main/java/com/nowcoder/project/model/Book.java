package com.nowcoder.project.model;

/**图书实体类
 * @author zhangzq
 * @date 2023/5/6 9:35
 */
public class Book {

  /**
   * 图书ID
   */
  private int id;

  /**
   * 书名
   */
  private String name;

  /**
   * 作者
   */
  private String author;

  /**
   * 价格
   */
  private String price;

  /**
   * 状态
   * {@link com.nowcoder.project.model.enums.BookStatusEnum}
   */
  private int status;

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
