package ro.nexttech.poc.spring.transactionality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.nexttech.poc.spring.transactionality.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
