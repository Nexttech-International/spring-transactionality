package ro.nexttech.poc.spring.transactionality.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.nexttech.poc.spring.transactionality.repository.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static ro.nexttech.poc.spring.transactionality.service.Constants.*;

/**
 * Testing {@link OrdersAndPaymentsService#addOrderAndPayment_transactional}
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Test02OrdersAndPaymentsServiceImplTransactional {

    @SpyBean
    private OrderRepository orderRepository;
    @SpyBean
    private PaymentRepository paymentRepository;
    @Autowired
    private OrdersAndPaymentsService service;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addOrderAndPayment_transactional() {
        service.addOrderAndPayment_transactional(orderEntity, paymentEntity);

        assertEquals(1, orderRepository.count());
        assertEquals(1, paymentRepository.count());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addOrderAndPayment_transactional_throwException() {
        doThrow(new RuntimeException(ERROR_MESSAGE)).when(paymentRepository).save(paymentEntity);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> service.addOrderAndPayment_transactional(orderEntity, paymentEntity));

        assertEquals(ERROR_MESSAGE, runtimeException.getMessage());
        assertEquals(0, orderRepository.count());
        assertEquals(0, paymentRepository.count());
    }
}
