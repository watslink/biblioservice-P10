package com.sd.oc.Service.ServiceImpl;

import com.sd.oc.Service.ConfigurationService;
import com.sd.oc.Service.ServiceInterface.BookService;
import com.sd.oc.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigurationService.class)
public class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Test
    public void getBook() {
        assertEquals("Contes", bookService.getBook(1).getTitle());
    }

    @Test
    @Transactional
    @Rollback
    public void updateBook() {
        Book book = bookService.getBook(1);
        book.setTitle("TestUpdate");
        bookService.updateBook(book);
        assertEquals("TestUpdate", bookService.getBook(1).getTitle());
    }

    @Test
    public void getAllBook() {
        assertEquals(9, bookService.getAllBook().size());
    }
}
