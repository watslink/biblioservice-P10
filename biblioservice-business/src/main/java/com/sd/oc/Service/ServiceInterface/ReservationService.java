package com.sd.oc.Service.ServiceInterface;

import com.sd.oc.model.Book;
import com.sd.oc.model.Reservation;
import com.sd.oc.model.User;

import java.util.List;

public interface ReservationService {

    Reservation getReservation(int id);

    List<Reservation> getAllReservation();

    void addReservation(Book book, User user);

    void deleteReservation(Reservation reservation);

    void updateReservation(Reservation reservation);

    List<Reservation>getAllReservationOfUser(User user);

    List<Reservation>getAllReservationOfBookOrderByReturnDate(Book book);
}
