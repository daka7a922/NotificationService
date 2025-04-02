package com.eventsphere.notificationservice.repository;

import com.eventsphere.notificationservice.model.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

}
