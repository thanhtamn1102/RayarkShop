package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "sales_person")
public class SalesPerson extends Employee {

}
