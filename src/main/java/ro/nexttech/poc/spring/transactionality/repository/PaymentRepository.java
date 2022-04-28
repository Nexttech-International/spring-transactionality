package ro.nexttech.poc.spring.transactionality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.nexttech.poc.spring.transactionality.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
}
