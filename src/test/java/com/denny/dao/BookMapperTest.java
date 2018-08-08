package com.denny.dao;

import com.denny.model.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by denny.wang on 2018/8/7.
 */

public class BookMapperTest {
    @Autowired
    @Qualifier("bookMapper")
    BookMapper bookMapper;

    @Test
    public void testSelectByPrimaryKey(){
        long bookId = 1000;
        Book book = bookMapper.selectByPrimaryKey(bookId);
        System.out.println(book);
    }
}
