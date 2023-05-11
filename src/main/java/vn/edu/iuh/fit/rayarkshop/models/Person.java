package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_entity_id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    public Person(String firstName, String middleName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
}
