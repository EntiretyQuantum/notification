package com.example.notification.api

import com.example.notification.AccessToken
import com.example.notification.model.Notification
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationInterface {

    @POST("/v1/projects/notification-426dd/messages:send")
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )

    fun notification(
        @Body message: Notification,
        @Header("Authorization") accessToken: String = "Bearer ${AccessToken.getAccessToken()}"
    ) : Call<Notification>

}
