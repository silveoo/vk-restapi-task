package org.example.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    boolean usersAllowed;

    boolean albumsAllowed;

    boolean postsAllowed;

}
