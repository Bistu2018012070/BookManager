package com.nowcoder.project.model;

/**
 * Created by nowcoder on 2018/08/04 下午3:41
 */
public class User {

  /**
   * 用户实体
   */
  private int id;

  /**
   * 用户名
   */
  private String name;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 密码
   * 经过md5加密
   */
  private String password;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
