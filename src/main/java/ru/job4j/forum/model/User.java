package ru.job4j.forum.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Model of user
 */
@Component
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String email;

    private String password;

    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;
}
