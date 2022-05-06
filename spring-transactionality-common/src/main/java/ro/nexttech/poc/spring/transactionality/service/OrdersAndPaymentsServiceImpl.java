package ro.nexttech.poc.spring.transactionality.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.PaymentRepository;

@Service
@RequiredArgsConstructor
public class OrdersAndPaymentsServiceImpl implements OrdersAndPaymentsService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public void addOrderAndPayment_nonTransactional(OrderEntity orderEntity, PaymentEntity paymentEntity) {
        orderRepository.save(orderEntity);
        paymentRepository.save(paymentEntity);
    }

    @Override
    @Transactional
    public void addOrderAndPayment_transactional(OrderEntity orderEntity, PaymentEntity paymentEntity) {
        orderRepository.save(orderEntity);
        paymentRepository.save(paymentEntity);
    }

    @Override
    @Transactional(transactionManager = "ordersAndPaymentsTransactionManager")
    public void addOrderAndPayment_transactional_ordersAndPaymentsTransactionManager(OrderEntity orderEntity, PaymentEntity paymentEntity) {
        orderRepository.save(orderEntity);
        paymentRepository.save(paymentEntity);
    }

    @Override
    @Transactional(transactionManager = "notificationsTransactionManager")
    public void addOrderAndPayment_transactional_notificationsTransactionManager(OrderEntity orderEntity, PaymentEntity paymentEntity) {
        orderRepository.save(orderEntity);
        paymentRepository.save(paymentEntity);
    }
}
