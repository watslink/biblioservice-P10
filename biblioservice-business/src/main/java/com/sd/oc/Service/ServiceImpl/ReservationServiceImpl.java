package com.sd.oc.Service.ServiceImpl;

import com.sd.oc.DAO.ReservationDAO;
import com.sd.oc.Service.ServiceInterface.BookService;
import com.sd.oc.Service.ServiceInterface.ReservationService;
import com.sd.oc.model.Book;
import com.sd.oc.model.Borrowing;
import com.sd.oc.model.Reservation;
import com.sd.oc.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService {


    @Autowired
    ReservationDAO reservationDAO;

    private static Logger logger = LogManager.getLogger("ReservationServiceImpl");

    @Override
    public Reservation getReservation(int id) {
        Optional<Reservation> optBorrowing=reservationDAO.findById(id);
        return optBorrowing.orElse(null);
    }

    @Override
    public List<Reservation> getAllReservation() {
        return (List<Reservation>)reservationDAO.findAll();
    }

    @Override
    public void addReservationBorrowing(Book book, User user) {
        boolean AlreadyBorrow= false;
        if(book.getNbStock() ==0){
            if(reservationDAO.findByBookOrderByDateReservation(book).size() < 2*book.getNbTotalExemplaire()){
                for(Borrowing borrowing : user.getListOfBorrowings()){
                    if(borrowing.getBook().equals(book)){
                        AlreadyBorrow = true;
                    }
                    logger.warn(("L'utilisateur emprunte déjà ce livre"));
                }
                if(!AlreadyBorrow){
                    reservationDAO.save(new Reservation(book, user, LocalDate.now()));
                    logger.info("reservation sauvegardée");
                }
            } else {
                logger.warn("Le nombre de réservations autorisées est déjà atteint");
            }
        } else {
            logger.warn("le stock n'est pas nul");
        }
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        reservationDAO.delete(reservation);
        logger.info("reservation supprimée "+ reservation.getReservation_id());
    }

    @Override
    public void updateReservation(Reservation reservation) {
        reservationDAO.save(reservation);
        logger.info("reservation mise à jour "+ reservation.getReservation_id());
    }

    @Override
    public List<Reservation> getAllReservationOfUser(User user) {
        return reservationDAO.findByUser(user);
    }

    @Override
    public List<Reservation> getAllReservationOfBookOrderByReturnDate(Book book) {
        return reservationDAO.findByBookOrderByDateReservation(book);
    }
}
