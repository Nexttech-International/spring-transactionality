package ro.nexttech.poc.spring.transactionality.repository.ordersandpayments;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
