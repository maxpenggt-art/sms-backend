package net.javaguides.sms.entity;

import jakarta.persistence.*; // This covers Entity, Table, Id, Column, etc.
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = true)
    private Sex sex;

    @Column(name = "score")
    private Double score;

    @Column(name = "age")
    private Integer age;

    @Column(name = "class")
    private String className;

}
