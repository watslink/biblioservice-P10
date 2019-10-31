package com.sd.oc.API;


import com.sd.oc.Service.ServiceInterface.BookService;
import com.sd.oc.model.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "BookAPI")
public class BookServiceAPI {

    private ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationAPI.class);

    private BookService bookService = context.getBean(BookService.class);

    @WebMethod(operationName = "findBookById")
    public Book findBookById(@WebParam(name = "book_id") int id) {
        return bookService.getBook(id);
    }

    @WebMethod(operationName = "findAllBooks")
    public List<Book> findAll() {
        return bookService.getAllBook();
    }
}
