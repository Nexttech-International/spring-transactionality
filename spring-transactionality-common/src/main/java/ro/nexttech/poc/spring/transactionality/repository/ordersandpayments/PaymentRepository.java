package ro.nexttech.poc.spring.transactionality.repository.ordersandpayments;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
}
