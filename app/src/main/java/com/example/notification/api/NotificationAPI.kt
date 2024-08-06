package com.example.notification.api

import com.example.notification.AccessToken
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NotificationAPI {
    private const val BASE_URL = "https://fcm.googleapis.com"

    private val client = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer ${AccessToken.getAccessToken()}")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }.build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun sendNotification(): NotificationInterface {
        return retrofit.create(NotificationInterface::class.java)
    }
}
