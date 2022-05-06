package ro.nexttech.poc.spring.transactionality.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.nexttech.poc.spring.transactionality.entity.notifications.NotificationEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;
import ro.nexttech.poc.spring.transactionality.repository.notifications.NotificationRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.PaymentRepository;

@Service
@RequiredArgsConstructor
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
    @Transactional
    public void addOrderPaymentAndNotification_transactional(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity) {
        orderRepository.save(orderEntity);
        paymentRepository.save(paymentEntity);
        notificationRepository.save(notificationEntity);
    }

    @Override
    @Transactional(transactionManager = "ordersAndPaymentsTransactionManager")
    public void addOrderPaymentAndNotification_transactional_ordersAndPaymentsTransactionManager(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity) {
        orderRepository.save(orderEntity);
        paymentRepository.save(paymentEntity);
        notificationRepository.save(notificationEntity);
    }

    @Override
    @Transactional(transactionManager = "notificationsTransactionManager")
    public void addOrderPaymentAndNotification_transactional_notificationsTransactionManager(OrderEntity orderEntity, PaymentEntity paymentEntity, NotificationEntity notificationEntity) {
        orderRepository.save(orderEntity);
        paymentRepository.save(paymentEntity);
        notificationRepository.save(notificationEntity);
    }
}
