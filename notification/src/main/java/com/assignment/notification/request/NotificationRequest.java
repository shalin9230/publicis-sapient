package com.assignment.notification.request;

public record NotificationRequest(
        Long toUserId,
        String toUserEmail,
        String message
) {
}
