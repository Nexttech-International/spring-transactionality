package ro.nexttech.poc.spring.transactionality.service;

import ro.nexttech.poc.spring.transactionality.entity.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.PaymentEntity;

public class Constants {

    public static final String ERROR_MESSAGE = "Exception while persisting to db!";

    public static final OrderEntity orderEntity = new OrderEntity(null, 25.04);
    public static final PaymentEntity paymentEntity = new PaymentEntity(null, "CASH");
}
