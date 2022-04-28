package ro.nexttech.poc.spring.transactionality.entity.notifications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "NOTIFICATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SEQ_NOTIFICATIONS")
    @GenericGenerator(name = "SEQ_NOTIFICATIONS", strategy = "uuid2")
    private String id;
}
