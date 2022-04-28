package ro.nexttech.poc.spring.transactionality.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.nexttech.poc.spring.transactionality.dto.OrdersAndPaymentsDTO;
import ro.nexttech.poc.spring.transactionality.service.OrdersAndPaymentsService;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "Orders and Payments Controller")
public class OrdersAndPaymentsController {

    private final OrdersAndPaymentsService ordersAndPaymentsService;

    @GetMapping("/ordersandpayments")
    public ResponseEntity<OrdersAndPaymentsDTO> findOrdersAndPayments() {
        return new ResponseEntity<>(ordersAndPaymentsService.findOrdersAndPayments(), HttpStatus.OK);
    }
}
