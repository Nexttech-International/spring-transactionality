package ro.nexttech.poc.spring.transactionality.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.nexttech.poc.spring.transactionality.Constants;
import ro.nexttech.poc.spring.transactionality.entity.notifications.NotificationEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;
import ro.nexttech.poc.spring.transactionality.repository.notifications.NotificationRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

/**
 * Testing {@link OrdersPaymentsAndNotificationsServiceMultipleTM#addOrderPaymentAndNotification_transactional_ordersAndPaymentsTransactionManager(OrderEntity, PaymentEntity, NotificationEntity)}
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Test07OrdersPaymentsAndNotificationsServiceMultipleTMOrdersAndPaymentsTM {

    @SpyBean
    private OrderRepository orderRepository;
    @SpyBean
    private PaymentRepository paymentRepository;
    @SpyBean
    private NotificationRepository notificationRepository;

    @Autowired
    private OrdersPaymentsAndNotificationsServiceMultipleTM service;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addOrderPaymentAndNotification_transactional_ordersAndPaymentsTransactionManager() {
        service.addOrderPaymentAndNotification_transactional_ordersAndPaymentsTransactionManager(Constants.orderEntity, Constants.paymentEntity, Constants.notificationEntity);

        assertEquals(1, orderRepository.count());
        assertEquals(1, paymentRepository.count());
        assertEquals(1, notificationRepository.count());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addOrderPaymentAndNotification_transactional_ordersAndPaymentsTransactionManager_throwException1() {
        doThrow(new RuntimeException(Constants.ERROR_MESSAGE)).when(paymentRepository).save(Constants.paymentEntity);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> service.addOrderPaymentAndNotification_transactional_ordersAndPaymentsTransactionManager(Constants.orderEntity, Constants.paymentEntity, Constants.notificationEntity));

        Assertions.assertEquals(Constants.ERROR_MESSAGE, runtimeException.getMessage());
        assertEquals(0, orderRepository.count());
        assertEquals(0, paymentRepository.count());
        assertEquals(0, notificationRepository.count());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addOrderPaymentAndNotification_transactional_ordersAndPaymentsTransactionManager_throwException2() {
        doThrow(new RuntimeException(Constants.ERROR_MESSAGE)).when(notificationRepository).save(Constants.notificationEntity);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> service.addOrderPaymentAndNotification_transactional_ordersAndPaymentsTransactionManager(Constants.orderEntity, Constants.paymentEntity, Constants.notificationEntity));

        Assertions.assertEquals(Constants.ERROR_MESSAGE, runtimeException.getMessage());
        assertEquals(0, orderRepository.count());
        assertEquals(0, paymentRepository.count());
        assertEquals(0, notificationRepository.count());
    }
}
