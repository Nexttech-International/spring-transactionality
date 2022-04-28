package ro.nexttech.poc.spring.transactionality.service;

import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;
import ro.nexttech.poc.spring.transactionality.entity.notifications.NotificationEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;

public interface OrdersPaymentsAndNotificationsService {

    void addOrderPaymentAndNotification_nonTransactional(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity);

    void addOrderPaymentAndNotification_transactional(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity);
}
