package ro.nexttech.poc.spring.transactionality.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SEQ_PAYMENTS")
    @GenericGenerator(name = "SEQ_PAYMENTS", strategy = "uuid2")
    private String id;

    @Column(name = "PAYMENT_METHOD")
    private String name;
}
