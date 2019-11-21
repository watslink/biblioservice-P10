package com.sd.oc.Service.ServiceImpl;

import com.sd.oc.Service.ConfigurationService;
import com.sd.oc.Service.ServiceInterface.ReservationService;
import com.sd.oc.model.Book;
import com.sd.oc.model.Borrowing;
import com.sd.oc.model.Reservation;
import com.sd.oc.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigurationService.class)
public class ReservationServiceImplTest {

    @Autowired
    ReservationService reservationService;

    @Test
    public void getReservation() {
        assertEquals("melissa.hamon", reservationService.getReservation(2).getUser().getUsername());
        assertEquals("Le retour du roi", reservationService.getReservation(2).getBook().getTitle());
    }

    @Test
    public void getAllReservation() {
        assertEquals(5, reservationService.getAllReservation().size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    @Rollback
    @Transactional
    public void addReservationWithTooMuchReservation() {
        Book book = new Book("Shining", "Stephen King", 1979, 254, 0, 1);
        User user = new User();
        book.setBook_id(8);
        user.setUser_id(10);
        user.setListOfBorrowings(new ArrayList<Borrowing>());
        reservationService.addReservation(book, user);
        reservationService.getAllReservationOfUser(user).get(0);
    }

    @Test
    @Rollback
    @Transactional
    public void addReservation() {
        Book book = new Book("Le retour du roi", "Tolkien", 1973, 312, 0, 4);
        User user = new User();
        book.setBook_id(7);
        user.setUser_id(10);
        user.setListOfBorrowings(new ArrayList<Borrowing>());
        reservationService.addReservation(book, user);
        assertEquals(book.getBook_id(), reservationService.getAllReservationOfUser(user).get(0).getBook().getBook_id());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    @Transactional
    @Rollback
    public void addReservationWithAlreadyBorrowBook() {
        Book book = new Book("Le retour du roi", "Tolkien", 1973, 312, 0, 4);
        User user = new User();
        book.setBook_id(7);
        user.setUser_id(10);
        Borrowing borrowing = new Borrowing(book, user, LocalDate.now());
        List<Borrowing> borrowings = new ArrayList<>();
        borrowings.add(borrowing);
        user.setListOfBorrowings(borrowings);
        reservationService.addReservation(book, user);
        reservationService.getAllReservationOfUser(user).get(0);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteReservation() {
        Book book = new Book("Le retour du roi", "Tolkien", 1973, 312, 0, 4);
        User user = new User();
        book.setBook_id(7);
        user.setUser_id(1);
        Reservation reservation =new Reservation(book, user, LocalDate.now());
        reservation.setReservation_id(2);
        reservationService.deleteReservation(reservation);
        assertNull(reservationService.getReservation(2));
    }

    @Test
    @Transactional
    @Rollback
    public void updateReservation() {
        Book book = new Book("Le retour du roi", "Tolkien", 1973, 312, 0, 4);
        User user = new User();
        book.setBook_id(7);
        user.setUser_id(1);
        Reservation reservation =new Reservation(book, user, LocalDate.now());
        reservation.setReservation_id(2);
        assertNull(reservation.getDateStartMailing());
        reservation.setDateStartMailing(LocalDate.now());
        reservationService.updateReservation(reservation);
        assertNotNull(reservationService.getReservation(2).getDateStartMailing());
    }

    @Test
    public void getAllReservationOfUser() {
        User user = new User();
        user.setUser_id(1);
        assertEquals(2, reservationService.getAllReservationOfUser(user).size());
    }

    @Test
    public void getAllReservationOfBookOrderByReturnDate() {
        Book book = new Book("Le retour du roi", "Tolkien", 1973, 312, 0, 4);
        book.setBook_id(7);
        assertEquals(1, reservationService.getAllReservationOfBookOrderByReturnDate(book).size());
    }

    @Test
    @Transactional
    @Rollback
    public void deleteAllReservationOutOfDate() {
        reservationService.deleteAllReservationOutOfDate();
        assertNull(reservationService.getReservation(1));
    }
}
