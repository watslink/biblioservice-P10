package com.sd.oc.API;

import com.sd.oc.Service.ServiceInterface.BookService;
import com.sd.oc.Service.ServiceInterface.BorrowingService;
import com.sd.oc.Service.ServiceInterface.UserService;
import com.sd.oc.model.Book;
import com.sd.oc.model.Borrowing;
import com.sd.oc.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "BorrowingAPI")
public class BorrowingServiceAPI {

    private ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationAPI.class);

    private BorrowingService borrowingService = context.getBean(BorrowingService.class);
    private BookService bookService = context.getBean(BookService.class);
    private UserService userService = context.getBean(UserService.class);

    @WebMethod(operationName = "findAllBorrowing")
    public List<Borrowing> findAllBorrowing() {
        return borrowingService.getAllBorrowing();
    }

    @WebMethod(operationName = "addBorrowing")
    public void addBorrowing(@WebParam(name = "book_id") int book_id,
                             @WebParam(name = "user_id") int user_id) {
        Book book = bookService.getBook(book_id);
        User user = userService.getUserById(user_id);
        borrowingService.addBorrowing(book, user);
    }

    @WebMethod(operationName = "deleteBorrowing")
    public void deleteBorrowing(@WebParam(name = "borrowing_id") int id) {
        Borrowing borrowing = borrowingService.getBorrowing(id);
        borrowingService.deleteBorrowing(borrowing);
    }

    @WebMethod(operationName = "findAllBorrowingOfUser")
    public List<Borrowing> findAllBorrowingOfUser(@WebParam(name = "user_id") int id) {
       return borrowingService.getAllBorrowingOfUser(userService.getUserById(id));
    }

    @WebMethod(operationName="findAllBorrowingOutOfTime")
    public List<Borrowing> findAllBorrowingOutOfTime(){
        return borrowingService.getAllBorrowingOutOfTime();
    }

    @WebMethod(operationName = "findAllBorrowingOutOfTimeOfUser")
    public List<Borrowing> findAllBorrowingOutOfTimeOfUser(@WebParam(name = "user_id") int id) {
        return borrowingService.getAllBorrowingOutOfTimeOfUser(userService.getUserById(id));
    }

    @WebMethod(operationName = "extendBorrowing")
    public void extendBorrowing(@WebParam(name = "borrowing_id") int id){
        borrowingService.extendBorrowing(borrowingService.getBorrowing(id));
    }
}
