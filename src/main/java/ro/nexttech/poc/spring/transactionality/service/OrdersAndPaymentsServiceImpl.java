package ro.nexttech.poc.spring.transactionality.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.nexttech.poc.spring.transactionality.dto.OrdersAndPaymentsDTO;
import ro.nexttech.poc.spring.transactionality.entity.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.PaymentEntity;
import ro.nexttech.poc.spring.transactionality.repository.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersAndPaymentsServiceImpl implements OrdersAndPaymentsService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public OrdersAndPaymentsDTO findOrdersAndPayments() {
        List<OrderEntity> orders = orderRepository.findAll();
        List<PaymentEntity> payments = paymentRepository.findAll();

        return new OrdersAndPaymentsDTO(orders, payments);
    }
}
