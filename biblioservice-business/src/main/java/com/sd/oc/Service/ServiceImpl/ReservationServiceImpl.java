package com.sd.oc.Service.ServiceImpl;

import com.sd.oc.Service.ServiceInterface.ReservationService;
import com.sd.oc.model.Book;
import com.sd.oc.model.Reservation;
import com.sd.oc.model.User;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    @Override
    public Reservation getReservation(int id) {
        return null;
    }

    @Override
    public List<Reservation> getAllReservation() {
        return null;
    }

    @Override
    public void addReservationBorrowing(Book book, User user) {

    }

    @Override
    public void deleteReservation(Reservation reservation) {

    }

    @Override
    public void updateReservation(Reservation reservation) {

    }

    @Override
    public List<Reservation> getAllReservationOfUser(User user) {
        return null;
    }

    @Override
    public List<Reservation> getAllReservationOfBookOrderByReturnDate(Book book) {
        return null;
    }
}
