package ro.nexttech.poc.spring.transactionality;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.ordersandpayments.PaymentRepository;
import ro.nexttech.poc.spring.transactionality.service.OrdersAndPaymentsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

/**
 * Testing {@link OrdersAndPaymentsService#addOrderAndPayment_nonTransactional(OrderEntity, PaymentEntity)}
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Test01OrdersAndPaymentsServiceNonTransactional {

    @SpyBean
    private OrderRepository orderRepository;
    @SpyBean
    private PaymentRepository paymentRepository;

    @Autowired
    private OrdersAndPaymentsService service;

    @Test
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    public void addOrderAndPayment_nonTransactional() {
        service.addOrderAndPayment_nonTransactional(Constants.orderEntity, Constants.paymentEntity);

        assertEquals(1, orderRepository.count());
        assertEquals(1, paymentRepository.count());
    }

    @Test
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    public void addOrderAndPayment_nonTransactional_throwException() {
        doThrow(new RuntimeException(Constants.ERROR_MESSAGE)).when(paymentRepository).save(Constants.paymentEntity);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> service.addOrderAndPayment_nonTransactional(Constants.orderEntity, Constants.paymentEntity));

        assertEquals(Constants.ERROR_MESSAGE, runtimeException.getMessage());
        assertEquals(1, orderRepository.count());
        assertEquals(0, paymentRepository.count());
    }
}
