package com.sd.oc.Service.ServiceInterface;

import com.sd.oc.model.Book;
import com.sd.oc.model.Borrowing;
import com.sd.oc.model.User;

import java.util.List;

public interface BorrowingService {

    Borrowing getBorrowing(int id);

    List<Borrowing> getAllBorrowing();

    void addBorrowing(Book book, User user);

    void deleteBorrowing(Borrowing borrowing);

    void extendBorrowing(Borrowing borrowing);

    List<Borrowing>getAllBorrowingOfUser(User user);

    List<Borrowing> getAllBorrowingOutOfTime();

    List<Borrowing> getAllBorrowingOutOfTimeOfUser(User user);
}
