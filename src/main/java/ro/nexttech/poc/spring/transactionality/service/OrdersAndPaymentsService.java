package ro.nexttech.poc.spring.transactionality.service;

public interface OrdersAndPaymentsService {

    void addOrderAndPayment_noTransactional(double orderAmount, String paymentMethod);

    void addOrderAndPayment_noTransactional2(double orderAmount, String paymentMethod);
}
