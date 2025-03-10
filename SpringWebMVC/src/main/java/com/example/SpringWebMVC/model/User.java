package com.example.SpringWebMVC.model;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public User(String firstname, String lastname, String loginemail, String loginpass) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = loginemail;
        this.password = loginpass;


    }
    @Override
    public String toString() {
        return this.id+ " "+this.firstname + " " + this.lastname + " " + this.email;
    }
}
