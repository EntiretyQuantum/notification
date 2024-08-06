package com.example.notification.model

data class NotificationData(
    val topic: String? = null,
    val data: HashMap<String, String>
)