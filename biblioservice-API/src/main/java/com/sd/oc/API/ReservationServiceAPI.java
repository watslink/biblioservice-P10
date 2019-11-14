package com.sd.oc.API;


import com.sd.oc.Service.ServiceInterface.BookService;
import com.sd.oc.Service.ServiceInterface.ReservationService;
import com.sd.oc.Service.ServiceInterface.UserService;
import com.sd.oc.model.Book;
import com.sd.oc.model.Reservation;
import com.sd.oc.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.time.LocalDate;
import java.util.List;

@WebService(serviceName = "ReservationAPI")
public class ReservationServiceAPI {

    private ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationAPI.class);

    private ReservationService reservationService = context.getBean(ReservationService.class);
    private BookService bookService = context.getBean(BookService.class);
    private UserService userService = context.getBean(UserService.class);


    @WebMethod(operationName = "findAllReservations")
    public List<Reservation> findAllReservations() {
        return reservationService.getAllReservation();
    }

    @SuppressWarnings("ValidExternallyBoundObject")
    @WebMethod(operationName = "findReservation")
    public Reservation findById(@WebParam(name = "reservation_id") int reservation_id) {
        return reservationService.getReservation(reservation_id);
    }

    @WebMethod(operationName = "addReservation")
    public void addBorrowing(@WebParam(name = "book_id") int book_id,
                             @WebParam(name = "user_id") int user_id) {
        Book book = bookService.getBook(book_id);
        User user = userService.getUserById(user_id);
        reservationService.addReservation(book, user);
    }

    @WebMethod(operationName = "deleteReservation")
    public void deleteReservation(@WebParam(name = "reservation_id") int id) {
       Reservation reservation = reservationService.getReservation(id);
       reservationService.deleteReservation(reservation);
    }

    @WebMethod(operationName = "findAllReservationOfUser")
    public List<Reservation> findAllReservationOfUser(@WebParam(name = "user_id") int id) {
        return reservationService.getAllReservationOfUser(userService.getUserById(id));
    }

    @WebMethod(operationName = "findAllReservationOfBookOrderByDate")
    public List<Reservation> findAllReservationOfBookOrderByDate(@WebParam(name = "book_id") int id) {
        return reservationService.getAllReservationOfBookOrderByReturnDate(bookService.getBook(id));
    }

    @WebMethod(operationName = "setStartDateMailingOfReservation")
    public void setStartDateMailingOfReservation(@WebParam(name = "reservation_id") int id) {
        Reservation reservation = reservationService.getReservation(id);
        reservation.setDateStartMailing(LocalDate.now());
        reservationService.updateReservation(reservation);
    }

    @WebMethod(operationName = "deleteAllReservationOutOfDate")
    public void deleteAllReservationOutOfDate() {
        reservationService.deleteAllReservationOutOfDate();
    }
}
