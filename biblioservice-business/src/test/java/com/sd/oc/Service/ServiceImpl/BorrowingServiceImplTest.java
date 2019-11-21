package com.sd.oc.Service.ServiceImpl;

import com.sd.oc.Service.ConfigurationService;
import com.sd.oc.Service.ServiceInterface.BorrowingService;
import com.sd.oc.model.Book;
import com.sd.oc.model.Borrowing;
import com.sd.oc.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigurationService.class)
public class BorrowingServiceImplTest {

    @Autowired
    BorrowingService borrowingService;

    @Test
    public void getBorrowing() {
        assertEquals("melissa.hamon", borrowingService.getBorrowing(1).getUser().getUsername());
        assertEquals("Contes", borrowingService.getBorrowing(1).getBook().getTitle());
    }

    @Test
    public void getAllBorrowing() {
        assertEquals(12, borrowingService.getAllBorrowing().size());
    }

    @Test
    @Transactional
    @Rollback
    public void addBorrowing() {
        Book book = new Book("1984", "George Orwell", 1949, 198, 42, 42);
        User user = new User();
        book.setBook_id(4);
        user.setUser_id(4);
        borrowingService.addBorrowing(book, user);
        assertEquals(book.getBook_id(), borrowingService.getAllBorrowingOfUser(user).get(1).getBook().getBook_id());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    @Transactional
    @Rollback
    public void addBorrowingWithNoStock() {
        Book book = new Book("Le hobbit", "Tolkien", 1969, 354, 0, 1);
        User user = new User();
        book.setBook_id(8);
        user.setUser_id(4);
        borrowingService.addBorrowing(book, user);
        borrowingService.getAllBorrowingOfUser(user).get(1);
    }


    @Test
    @Transactional
    @Rollback
    public void deleteBorrowing() {
        Book book = new Book("Contes", "Hans Christen Andersen", 1837, 150, 1, 2);
        User user = new User();
        book.setBook_id(1);
        user.setUser_id(1);
        Borrowing borrowing = new Borrowing(book, user, LocalDate.now());
        borrowing.setBorrowing_id(1);
        borrowingService.deleteBorrowing(borrowing);
        assertNull(borrowingService.getBorrowing(1));
    }

    @Test
    @Transactional
    @Rollback
    public void extendBorrowing() {
        Book book = new Book("Contes", "Hans Christen Andersen", 1837, 150, 1, 2);
        User user = new User();
        book.setBook_id(1);
        user.setUser_id(1);
        Borrowing borrowing = new Borrowing(book, user, LocalDate.now());
        borrowing.setBorrowing_id(1);
        assertFalse(borrowingService.getBorrowing(1).isExtended());
        borrowingService.extendBorrowing(borrowing);
        assertTrue(borrowingService.getBorrowing(1).isExtended());
    }

    @Test
    public void getAllBorrowingOfUser() {
        User user = new User();
        user.setUser_id(1);
        List<Borrowing> borrowingsOfUser = borrowingService.getAllBorrowingOfUser(user);
        assertEquals(4, borrowingsOfUser.size());
    }

    @Test
    public void getAllBorrowingOutOfTime() {
        List<Borrowing> borrowings = borrowingService.getAllBorrowingOutOfTime();
        for(Borrowing borrowing: borrowings){
            assertTrue(borrowing.getReturnDate().isBefore(LocalDate.now()));
        }
    }

    @Test
    public void getAllBorrowingOutOfTimeOfUser() {
        User user = new User();
        user.setUser_id(1);
        List<Borrowing> borrowingsOfUser = borrowingService.getAllBorrowingOutOfTimeOfUser(user);
        for(Borrowing borrowing: borrowingsOfUser){
            assertTrue(borrowing.getReturnDate().isBefore(LocalDate.now()));
        }
    }

    @Test
    public void getAllBorrowingOfBookOrderByReturnDate() {
        Book book = new Book("Contes", "Hans Christen Andersen", 1837, 150, 1, 2);
        book.setBook_id(1);
        assertEquals(1, borrowingService.getAllBorrowingOfBookOrderByReturnDate(book).size());
    }
}
