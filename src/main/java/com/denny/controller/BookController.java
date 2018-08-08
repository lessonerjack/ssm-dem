package com.denny.controller;

import com.alibaba.fastjson.JSONObject;
import com.denny.model.Book;
import com.denny.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by denny.wang on 2018/8/7.
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/list", method = RequestMethod.GET,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String list(){

        List<Book> list = bookService.getList();
        // list.jsp + model = ModelAndView
        String s = JSONObject.toJSONString(list);
        return s;// WEB-INF/jsp/"list".jsp
    }
}
