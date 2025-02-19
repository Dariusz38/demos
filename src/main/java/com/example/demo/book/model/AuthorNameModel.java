package com.example.demo.book.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@Table(name = "AUTHORNAME")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorNameModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
//    @OneToMany( mappedBy = "customerModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

}
