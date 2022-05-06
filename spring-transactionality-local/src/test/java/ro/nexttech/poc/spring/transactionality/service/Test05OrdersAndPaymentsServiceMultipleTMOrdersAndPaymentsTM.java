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
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.ordersandpayments.PaymentEntity;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

/**
 * Testing {@link OrdersAndPaymentsServiceMultipleTM#addOrderAndPayment_transactional_ordersAndPaymentsTransactionManager(OrderEntity, PaymentEntity)}
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Test05OrdersAndPaymentsServiceMultipleTMOrdersAndPaymentsTM {

    @SpyBean
    private OrderRepository orderRepository;
    @SpyBean
    private PaymentRepository paymentRepository;

    @Autowired
    private OrdersAndPaymentsServiceMultipleTM service;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addOrderAndPayment_transactional_ordersAndPaymentsTransactionManager() {
        service.addOrderAndPayment_transactional_ordersAndPaymentsTransactionManager(Constants.orderEntity, Constants.paymentEntity);

        assertEquals(1, orderRepository.count());
        assertEquals(1, paymentRepository.count());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addOrderAndPayment_transactional_ordersAndPaymentsTransactionManager_throwException() {
        doThrow(new RuntimeException(Constants.ERROR_MESSAGE)).when(paymentRepository).save(Constants.paymentEntity);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> service.addOrderAndPayment_transactional_ordersAndPaymentsTransactionManager(Constants.orderEntity, Constants.paymentEntity));

        Assertions.assertEquals(Constants.ERROR_MESSAGE, runtimeException.getMessage());
        assertEquals(0, orderRepository.count());
        assertEquals(0, paymentRepository.count());
    }
}
