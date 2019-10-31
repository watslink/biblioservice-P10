
package com.sd.oc.model;


import com.sd.oc.utils.LocalDateXmlAdapter;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "borrowing")
@Getter
@Setter
@XmlRootElement
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowing_id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column (name = "return_date")
    private LocalDate returnDate;

    @Column
    private boolean extended;

    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    public LocalDate getReturnDate(){
        return returnDate;
    }

    public Borrowing() {
    }

    public Borrowing(Book book, User user, LocalDate returnDate) {
        this.book = book;
        this.user = user;
        this.returnDate = returnDate;
        this.extended = false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrowing borrowing = (Borrowing) o;
        return borrowing_id == borrowing.borrowing_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowing_id);
    }
}
