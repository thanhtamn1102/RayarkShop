package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "business_entity_id")
public class Employee extends Person {

    @Column(name = "cccd", length = 16, nullable = false)
    private String cccd;

    @OneToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;

}
