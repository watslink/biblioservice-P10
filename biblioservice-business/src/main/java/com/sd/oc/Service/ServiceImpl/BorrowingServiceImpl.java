package com.sd.oc.Service.ServiceImpl;

import com.sd.oc.DAO.BorrowingDAO;
import com.sd.oc.Service.ServiceInterface.BookService;
import com.sd.oc.Service.ServiceInterface.BorrowingService;
import com.sd.oc.model.Book;
import com.sd.oc.model.Borrowing;
import com.sd.oc.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
@PropertySource("classpath:borrowing.properties")
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    BorrowingDAO borrowingDAO;

    @Autowired
    BookService bookService;

    @Value("${weekOfBorrowing}")
    int weekOfBorrowing;

    @Value("${extendWeek}")
    int extendWeek;

    private static Logger logger = LogManager.getLogger("BorrowingServiceImpl");

    @Override
    public Borrowing getBorrowing(int id) {
        Optional<Borrowing> optBorrowing=borrowingDAO.findById(id);
        return optBorrowing.orElse(null);
    }

    @Override
    public List<Borrowing> getAllBorrowing() {
        return (List<Borrowing>)borrowingDAO.findAll();
    }

    @Override
    public void addBorrowing(Book book, User user) {
        if(book.getNbStock()>=1){
            book.setNbStock(book.getNbStock()-1);
            bookService.updateBook(book);
            Borrowing borrowing=new Borrowing(book, user, LocalDate.now().plus(weekOfBorrowing, ChronoUnit.WEEKS));
            borrowingDAO.save(borrowing);
            logger.info("saving borrowing id="+borrowing.getBorrowing_id());
        }
        else
            logger.warn("Book id="+book.getBook_id()+" cannat be borrow-> stock=0");
    }

    @Override
    public void deleteBorrowing(Borrowing borrowing) {
        if(!(borrowing==null)){
            Book book=borrowing.getBook();
            book.setNbStock(book.getNbStock()+1);
            bookService.updateBook(book);
            borrowingDAO.delete(borrowing);
            logger.info("borrowing id="+borrowing.getBorrowing_id()+" is deleted");
        }
        else
            logger.warn("this borrowing doesn't exist");
    }

    @Override
    public void extendBorrowing(Borrowing borrowing) {
        if(!borrowing.isExtended()){
            borrowing.setReturnDate(borrowing.getReturnDate().plus(extendWeek, ChronoUnit.WEEKS));
            borrowing.setExtended(true);
            borrowingDAO.save(borrowing);
            logger.info("borrowing id="+borrowing.getBorrowing_id()+" is extended");
        }
        else
            logger.warn("borrowing id="+borrowing.getBorrowing_id()+" is already extended");
    }

    @Override
    public List<Borrowing> getAllBorrowingOfUser(User user) {
        return borrowingDAO.findByUser(user);
    }

    @Override
    public List<Borrowing> getAllBorrowingOutOfTime() {
        return borrowingDAO.findByReturnDateBefore(LocalDate.now());
    }

    @Override
    public List<Borrowing> getAllBorrowingOutOfTimeOfUser(User user){
        return borrowingDAO.findByUserAndReturnDateBefore(user, LocalDate.now());
    }
}
