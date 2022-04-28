package ro.nexttech.poc.spring.transactionality.service;

import ro.nexttech.poc.spring.transactionality.entity.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.PaymentEntity;

public interface OrdersAndPaymentsService {

    void addOrderAndPayment_nonTransactional(OrderEntity orderEntity, PaymentEntity paymentEntity);

    void addOrderAndPayment_transactional(OrderEntity orderEntity, PaymentEntity paymentEntity);
}
