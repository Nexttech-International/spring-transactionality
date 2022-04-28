package ro.nexttech.poc.spring.transactionality.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.nexttech.poc.spring.transactionality.repository.OrderRepository;
import ro.nexttech.poc.spring.transactionality.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrdersAndPaymentsServiceImplTest {

    @Autowired
    private OrdersAndPaymentsService service;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void cleanupDatabase() {
        orderRepository.deleteAll();
        paymentRepository.deleteAll();
    }

    @Test
    void addOrderAndPayment_noTransactional() {
        service.addOrderAndPayment_noTransactional(25.04, "CASH");

        assertEquals(1, orderRepository.count());
        assertEquals(1, paymentRepository.count());
    }

    @Test
    void addOrderAndPayment_noTransactional2() {
        service.addOrderAndPayment_noTransactional2(25.04, "CASH");

        assertEquals(1, orderRepository.count());
        assertEquals(1, paymentRepository.count());
    }
}
