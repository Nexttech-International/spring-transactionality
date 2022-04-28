package ro.nexttech.poc.spring.transactionality.service;

import ro.nexttech.poc.spring.transactionality.dto.OrdersAndPaymentsDTO;

public interface OrdersAndPaymentsService {

    OrdersAndPaymentsDTO findOrdersAndPayments();
}
