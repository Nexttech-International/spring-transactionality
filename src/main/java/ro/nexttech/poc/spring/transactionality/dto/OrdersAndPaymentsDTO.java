package ro.nexttech.poc.spring.transactionality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.nexttech.poc.spring.transactionality.entity.OrderEntity;
import ro.nexttech.poc.spring.transactionality.entity.PaymentEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersAndPaymentsDTO {

    List<OrderEntity> orders;
    List<PaymentEntity> payments;
}
