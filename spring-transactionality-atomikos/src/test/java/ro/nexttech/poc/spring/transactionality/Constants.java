package ro.nexttech.poc.spring.transactionality;

import ro.nexttech.poc.spring.transactionality.entity.notifications.NotificationEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;

public class Constants {

    public static final String ERROR_MESSAGE = "Exception while persisting to db!";

    public static final OrderEntity orderEntity = new OrderEntity(null);
    public static final PaymentEntity paymentEntity = new PaymentEntity(null);
    public static final NotificationEntity notificationEntity = new NotificationEntity(null);
}
