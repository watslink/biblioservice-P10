package com.sd.oc.Service.ServiceInterface;

import com.sd.oc.model.Book;

import java.util.List;

public interface BookService {

    Book getBook(int id);

    void updateBook(Book book);

    List<Book> getAllBook();
}
