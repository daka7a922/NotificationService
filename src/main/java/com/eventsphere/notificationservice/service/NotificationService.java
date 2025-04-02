package com.eventsphere.notificationservice.service;

import com.eventsphere.notificationservice.model.NotificationLog;
import com.eventsphere.notificationservice.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationLogRepository repository;

    @Autowired
    public NotificationService(NotificationLogRepository repository) {
        this.repository = repository;
    }


    public NotificationLog recordNotification(NotificationLog logRequest) {

        logRequest.setTimestamp(LocalDateTime.now());

        System.out.println("Recording notification for: " + logRequest.getRecipient() + " | Message: " + logRequest.getMessage());
        return repository.save(logRequest);
    }

    public List<NotificationLog> getAllNotifications() {
        System.out.println("Fetching all notification logs.");
        return repository.findAll();
    }


    public NotificationLog getNotificationById(Long id) {
        System.out.println("Fetching notification log by ID: " + id);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }
}