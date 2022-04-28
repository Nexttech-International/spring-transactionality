package ro.nexttech.poc.spring.transactionality.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.nexttech.poc.spring.transactionality.entity.notifications.NotificationEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;
import ro.nexttech.poc.spring.transactionality.repository.notifications.NotificationRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.PaymentRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersPaymentsAndNotificationsServiceImpl implements OrdersPaymentsAndNotificationsService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public void addOrderPaymentAndNotification_nonTransactional(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity) {
        orderRepository.save(orderEntity);
        paymentRepository.save(paymentEntity);
        notificationRepository.save(notificationEntity);
    }

    @Override
    public void addOrderPaymentAndNotification_transactional(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity) {
        orderRepository.save(orderEntity);
        paymentRepository.save(paymentEntity);
        notificationRepository.save(notificationEntity);
    }
}
