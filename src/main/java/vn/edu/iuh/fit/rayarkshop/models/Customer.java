package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    @EqualsAndHashCode.Include
    private int id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Customer(Person person) {
        this.person = person;
    }
}
