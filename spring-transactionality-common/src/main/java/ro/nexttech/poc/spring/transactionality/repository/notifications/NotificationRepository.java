package ro.nexttech.poc.spring.transactionality.repository.notifications;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.nexttech.poc.spring.transactionality.entity.notifications.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, String> {
}
