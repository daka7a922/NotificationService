package com.eventsphere.notificationservice.web;

import com.eventsphere.notificationservice.model.NotificationLog;
import com.eventsphere.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * POST Endpoint: Records a notification log.
     * Expects a JSON body matching the NotificationLog structure (without id/timestamp).
     * Example Body: {"message": "Event Created", "recipient": "user@example.com", "eventReference": "event123"}
     */
    @PostMapping
    public ResponseEntity<NotificationLog> createNotificationLog(@RequestBody NotificationLog notificationLog) {

        if (notificationLog.getMessage() == null || notificationLog.getRecipient() == null) {
            return ResponseEntity.badRequest().build();
        }
        NotificationLog createdLog = notificationService.recordNotification(notificationLog);
        return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
    }

    /**
     * GET Endpoint: Retrieves all notification logs.
     */
    @GetMapping
    public ResponseEntity<List<NotificationLog>> getAllNotificationLogs() {
        List<NotificationLog> logs = notificationService.getAllNotifications();
        return ResponseEntity.ok(logs);
    }

    /**
     * GET Endpoint: Retrieves a specific notification log by ID.
     * Example URL: /api/v1/notifications/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<NotificationLog> getNotificationLogById(@PathVariable Long id) {
        try {
            NotificationLog log = notificationService.getNotificationById(id);
            return ResponseEntity.ok(log);
        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }
}