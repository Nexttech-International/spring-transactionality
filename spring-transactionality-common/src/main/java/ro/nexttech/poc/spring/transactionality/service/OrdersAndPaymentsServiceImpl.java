package ro.nexttech.poc.spring.transactionality.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.PaymentRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
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
}
