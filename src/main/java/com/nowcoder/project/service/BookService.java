package com.nowcoder.project.service;


import com.nowcoder.project.dao.BookDAO;
import com.nowcoder.project.model.Book;
import com.nowcoder.project.model.enums.BookStatusEnum;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图书管理业务层
 * @author zhangzq
 * @date 2023/5/6 9:41
 */
@Service
public class BookService {

  @Autowired
  private BookDAO bookDAO;

  /**
   * 查询所有书
   */
  public List<Book> getAllBooks() {
    return bookDAO.selectAll();
  }

  /**
   * 添加书
   * @param book
   * @return
   */
  public int addBooks(Book book) {
    return bookDAO.addBook(book);
  }

  /**
   * 删除书
   * @param id
   */
  public void deleteBooks(int id) {
    bookDAO.updateBookStatus(id, BookStatusEnum.DELETE.getValue());
  }

  /**
   * 启用书
   * @param id
   */
  public void recoverBooks(int id) {
    bookDAO.updateBookStatus(id, BookStatusEnum.NORMAL.getValue());
  }
}
