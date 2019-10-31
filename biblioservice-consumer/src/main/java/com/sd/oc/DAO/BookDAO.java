package com.sd.oc.DAO;

import com.sd.oc.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDAO extends CrudRepository<Book, Integer> {
}
