package com.assignment.user.request;

public record NotificationRequest(
        Long toUserId,
        String toUserEmail,
        String message
) {
}
