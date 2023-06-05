package com.nowcoder.project.controllers;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.nowcoder.project.handler.CustomSheetWriteHandler;
import com.nowcoder.project.listener.ExcelWithoutHeadListener;
import com.nowcoder.project.model.Book;
import com.nowcoder.project.model.User;
import com.nowcoder.project.model.vo.TaOrgImportVO;
import com.nowcoder.project.model.vo.TaUserImportVO;
import com.nowcoder.project.service.BookService;
import com.nowcoder.project.service.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by nowcoder on 2018/08/04 下午3:41
 */
@Controller
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private HostHolder hostHolder;

  @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
  public String bookList(Model model) {

    User host = hostHolder.getUser();
    if (host != null) {
      model.addAttribute("host", host);
    }
    loadAllBooksView(model);
    return "book/books";

  }

  @RequestMapping(path = {"/books/add"}, method = {RequestMethod.GET})
  public String addBook() {
    return "book/addbook";
  }

  /**
   * 下载模板（静态文件）
   * @param httpServletResponse
   */
  @RequestMapping(path = {"/books/download"}, method = {RequestMethod.GET})
  public void download(HttpServletResponse httpServletResponse) {
    InputStream inputStream = null;
    try (ServletOutputStream outputStream = httpServletResponse.getOutputStream()) {
      httpServletResponse.addHeader("content-disposition", String.format("attachment;filename= %s", URLEncoder.encode("导入模板.xlsx", "utf-8")));
      httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
      httpServletResponse.setCharacterEncoding("UTF-8");
      inputStream = new ClassPathResource("/senseXlsx.xlsx").getInputStream();
      byte[] b = streamToByteArray(inputStream);
      outputStream.write(b);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * 下载模板（动态生成）
   * @param response
   */
  @RequestMapping(path = {"/books/downloadDynamic"}, method = {RequestMethod.GET})
  public void downloadDynamic(HttpServletResponse response)throws IOException {
    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding("utf-8");
    String fileName = URLEncoder.encode("图书导入模板", "UTF-8");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    EasyExcel.write(response.getOutputStream(), Book.class).sheet("模板").doWrite(new ArrayList());

  }

  /**
   * 下载模板（用户）
   * @param response
   */
  @RequestMapping(path = {"/books/downloadUserExcel"}, method = {RequestMethod.GET})
  public void downloadUserExcel(HttpServletResponse response)throws IOException {
    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding("utf-8");
    String fileName = URLEncoder.encode("组织用户导入模板", "UTF-8");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    EasyExcel.write(response.getOutputStream(), TaUserImportVO.class).sheet("用户信息").doWrite(new ArrayList());

  }

  /**
   * 下载模板（组织）
   * @param response
   */
  @RequestMapping(path = {"/books/downloadOrgExcel"}, method = {RequestMethod.GET})
  public void downloadOrgExcel(HttpServletResponse response)throws IOException {
    List<TaOrgImportVO> list = new ArrayList<>();

    //定义一个map key是需要添加下拉框的列的index value是下拉框数据
    Map<Integer, String[]> mapDropDown = new HashMap<>();
    //设置是否车主下拉框数据
    String[] orgType = {"机构","部门"};

    //地区
    String[] area = {"西青区","南开区","河西区","和平区"};
;

    //下拉选在Excel中对应的列
    mapDropDown.put(1,orgType);
    mapDropDown.put(2,area);


    TaOrgImportVO bean = new TaOrgImportVO();
    bean.setNamePath("示例:天津市/和西区");
    bean.setOrgType("机构");
    bean.setArea("123400000011");
    bean.setCustomNo("100011");
    bean.setOrgCode("TJSHXQ");
    list.add(bean);

    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding("utf-8");
    String fileName = URLEncoder.encode("组织导入模板", "UTF-8");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    EasyExcel.write(response.getOutputStream(), TaOrgImportVO.class).sheet("组织信息").registerWriteHandler(new CustomSheetWriteHandler(mapDropDown)).doWrite(list);

  }

  @RequestMapping(path = {"/books/importOrg"}, method = {RequestMethod.GET})
  public String importOrg() {
    return "book/importOrg";
  }
  @RequestMapping(path = {"/books/importOrg/do"}, method = {RequestMethod.POST})
  public String doImportOrg(MultipartFile file,Model model) throws IOException{
    // 读取并解析Excel数据
    ExcelWithoutHeadListener<TaOrgImportVO> excelListener = new ExcelWithoutHeadListener<>();
    EasyExcelFactory.read(file.getInputStream(), TaOrgImportVO.class, excelListener).sheet().doRead();
    List<TaOrgImportVO> books = excelListener.getDataList();// 从excel里读取并解析好的数据
    List<TaOrgImportVO> successBooks = new ArrayList<>();
    List<TaOrgImportVO> failureBooks = new ArrayList<>();
    if (!ObjectUtils.isEmpty(books)){
      for (TaOrgImportVO book : books) {
        // List<String> allBooks = bookService.getAllBooks().stream().map(u -> u.getName()).collect(Collectors.toList());
        // // 校验状态
        // if (book.getStatus() != 1 && book.getStatus() != 0){
        //   book.setFailReason("状态值有误");
        //   failureBooks.add(book);
        //   continue;
        // }
        // // 校验是否重复
        // if (allBooks.contains(book.getName())){
        //   book.setFailReason("书名重复");
        //   failureBooks.add(book);
        //   continue;
        // }
        // successBooks.add(book);
        // bookService.addBooks(book);
      }
    }
    model.addAttribute("successBooks", successBooks);
    model.addAttribute("failureBooks", failureBooks);
    return "book/importBooks";
  }

  @RequestMapping(path = {"/books/import"}, method = {RequestMethod.GET})
  public String importBook() {
    return "book/importBooks";
  }

  @RequestMapping(path = {"/books/import/do"}, method = {RequestMethod.POST})
  public String doImportBook(MultipartFile file,Model model) throws IOException{
    // 读取并解析Excel数据
    ExcelWithoutHeadListener<Book> excelListener = new ExcelWithoutHeadListener<>();
    EasyExcelFactory.read(file.getInputStream(), Book.class, excelListener).sheet().doRead();
    List<Book> books = excelListener.getDataList();// 从excel里读取并解析好的数据
    List<Book> successBooks = new ArrayList<>();
    List<Book> failureBooks = new ArrayList<>();
    if (!ObjectUtils.isEmpty(books)){
      for (Book book : books) {
        List<String> allBooks = bookService.getAllBooks().stream().map(u -> u.getName()).collect(Collectors.toList());
        // 校验状态
        if (book.getStatus() != 1 && book.getStatus() != 0){
          book.setFailReason("状态值有误");
          failureBooks.add(book);
          continue;
        }
        // 校验是否重复
        if (allBooks.contains(book.getName())){
          book.setFailReason("书名重复");
          failureBooks.add(book);
          continue;
        }
        successBooks.add(book);
        bookService.addBooks(book);
      }
    }
    model.addAttribute("successBooks", successBooks);
    model.addAttribute("failureBooks", failureBooks);
    return "book/importBooks";
  }

  public static byte[] streamToByteArray(InputStream in) throws Exception {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096];
    int n;
    while (-1 != (n = in.read(buffer))) {
      output.write(buffer, 0, n);
    }
    return output.toByteArray();
  }


  @RequestMapping(path = {"/books/add/do"}, method = {RequestMethod.POST})
  public String doAddBook(
      @RequestParam("name") String name,
      @RequestParam("author") String author,
      @RequestParam("price") String price
  ) {

    Book book = new Book();
    book.setName(name);
    book.setAuthor(author);
    book.setPrice(price);
    bookService.addBooks(book);

    return "redirect:/index";

  }

  @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"}, method = {RequestMethod.GET})
  public String deleteBook(
      @PathVariable("bookId") int bookId
  ) {

    bookService.deleteBooks(bookId);
    return "redirect:/index";

  }

  @RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"}, method = {RequestMethod.GET})
  public String recoverBook(
      @PathVariable("bookId") int bookId
  ) {

    bookService.recoverBooks(bookId);
    return "redirect:/index";

  }

  /**
   * 为model加载所有的book
   */
  private void loadAllBooksView(Model model) {
    model.addAttribute("books", bookService.getAllBooks());
  }

}
