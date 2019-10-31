package com.sd.oc.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @Column
    private String title;

    @Column
    private String author;

    @Column(name = "publish_year")
    private int publishYear;

    @Column(name ="nb_pages")
    private int nbPages;

    @Column(name="number_in_stock")
    private int nbStock;

    public Book() {
    }

    public Book(String title, String author, int publishYear, int nbPages, int nbStock) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.nbPages = nbPages;
        this.nbStock = nbStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_id == book.book_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id);
    }
}
