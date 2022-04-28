package ro.nexttech.poc.spring.transactionality.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SEQ_ORDERS")
    @GenericGenerator(name = "SEQ_ORDERS", strategy = "uuid2")
    private String id;

    @Column(name = "AMOUNT")
    private Double amount;
}
