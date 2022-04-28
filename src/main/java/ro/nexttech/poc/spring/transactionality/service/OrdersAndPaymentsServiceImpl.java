package ro.nexttech.poc.spring.transactionality.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.nexttech.poc.spring.transactionality.entity.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.PaymentEntity;
import ro.nexttech.poc.spring.transactionality.repository.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersAndPaymentsServiceImpl implements OrdersAndPaymentsService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public void addOrderAndPayment_noTransactional(double orderAmount, String paymentMethod) {
        orderRepository.save(new OrderEntity(null, orderAmount));
        paymentRepository.save(new PaymentEntity(null, paymentMethod));
    }

    @Override
    public void addOrderAndPayment_noTransactional2(double orderAmount, String paymentMethod) {
        orderRepository.save(new OrderEntity(null, orderAmount));
        paymentRepository.save(new PaymentEntity(null, paymentMethod));
    }
}
