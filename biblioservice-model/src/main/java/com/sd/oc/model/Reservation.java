package com.sd.oc.model;

import com.sd.oc.utils.LocalDateXmlAdapter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@XmlRootElement
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservation_id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column (name = "date_mailing")
    @Nullable
    private LocalDate dateStartMailing;

    @Column (name = "date_of_reservation")
    private LocalDate dateReservation;

    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    public LocalDate getDateStartMailing(){
        return dateStartMailing;
    }

    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    public LocalDate getDateReservation(){
        return dateReservation;
    }

    public Reservation() {
    }

    public Reservation(Book book, User user, LocalDate dateReservation) {
        this.book = book;
        this.user = user;
        this.dateReservation = dateReservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservation_id == that.reservation_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservation_id);
    }
}
