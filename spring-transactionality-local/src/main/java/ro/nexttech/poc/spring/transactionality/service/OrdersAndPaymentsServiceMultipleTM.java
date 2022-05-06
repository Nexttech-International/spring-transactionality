package ro.nexttech.poc.spring.transactionality.service;

import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;

public interface OrdersAndPaymentsServiceMultipleTM {

    void addOrderAndPayment_transactional_ordersAndPaymentsTransactionManager(OrderEntity orderEntity, PaymentEntity paymentEntity);

    void addOrderAndPayment_transactional_notificationsTransactionManager(OrderEntity orderEntity, PaymentEntity paymentEntity);
}
