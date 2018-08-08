package com.denny.service.impl;


import com.denny.dao.BookMapper;
import com.denny.model.Book;
import com.denny.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入Service依赖
	@Autowired
	private BookMapper bookDao;
	@Override
	public Book getById(long bookId) {

		return bookDao.selectByPrimaryKey(bookId);
	}

	@Override
	public List<Book> getList() {
		return bookDao.queryAll(0, 1000);
	}


}
