package com.sd.oc.DAO;

import com.sd.oc.model.Book;
import com.sd.oc.model.Reservation;
import com.sd.oc.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationDAO extends CrudRepository<Reservation, Integer> {

    List<Reservation> findByUser(User user);

    List<Reservation> findByBookOrderByDateReservation(Book book);
}
