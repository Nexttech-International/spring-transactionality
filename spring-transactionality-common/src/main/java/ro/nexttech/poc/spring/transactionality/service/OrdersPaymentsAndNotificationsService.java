package ro.nexttech.poc.spring.transactionality.service;

import ro.nexttech.poc.spring.transactionality.entity.notifications.NotificationEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;

public interface OrdersPaymentsAndNotificationsService {

    void addOrderPaymentAndNotification_nonTransactional(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity);

    void addOrderPaymentAndNotification_transactional(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity);

    void addOrderPaymentAndNotification_transactional_ordersAndPaymentsTransactionManager(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity);

    void addOrderPaymentAndNotification_transactional_notificationsTransactionManager(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity);
}
