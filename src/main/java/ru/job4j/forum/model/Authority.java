package ru.job4j.forum.model;

import lombok.Data;
import javax.persistence.*;

/**
 * Model of role
 */
@Data
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String authority;
}
