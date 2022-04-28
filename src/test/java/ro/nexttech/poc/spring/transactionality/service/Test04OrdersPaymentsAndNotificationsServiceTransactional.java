package ro.nexttech.poc.spring.transactionality.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.nexttech.poc.spring.transactionality.entity.notifications.NotificationEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;
import ro.nexttech.poc.spring.transactionality.repository.notifications.NotificationRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static ro.nexttech.poc.spring.transactionality.service.Constants.*;

/**
 * Testing {@link OrdersPaymentsAndNotificationsService#addOrderPaymentAndNotification_transactional(OrderEntity, PaymentEntity, NotificationEntity)}
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Test04OrdersPaymentsAndNotificationsServiceTransactional {

    @SpyBean
    private OrderRepository orderRepository;
    @SpyBean
    private PaymentRepository paymentRepository;
    @SpyBean
    private NotificationRepository notificationRepository;

    @Autowired
    private OrdersPaymentsAndNotificationsService service;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addOrderPaymentAndNotification_transactional() {
        service.addOrderPaymentAndNotification_transactional(orderEntity, paymentEntity, notificationEntity);

        assertEquals(1, orderRepository.count());
        assertEquals(1, paymentRepository.count());
        assertEquals(1, notificationRepository.count());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addOrderPaymentAndNotification_transactional_throwException() {
        doThrow(new RuntimeException(ERROR_MESSAGE)).when(notificationRepository).save(notificationEntity);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> service.addOrderPaymentAndNotification_transactional(orderEntity, paymentEntity, notificationEntity));

        assertEquals(ERROR_MESSAGE, runtimeException.getMessage());
        assertEquals(1, orderRepository.count());
        assertEquals(1, paymentRepository.count());
        assertEquals(0, notificationRepository.count());
    }
}
