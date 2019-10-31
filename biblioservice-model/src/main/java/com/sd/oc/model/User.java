package com.sd.oc.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "users")
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String mail;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    @XmlTransient
    private List<Borrowing> listOfBorrowings;

    public User() {
    }

    public User(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.enabled = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }
}
