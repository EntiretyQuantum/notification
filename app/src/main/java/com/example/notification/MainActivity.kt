package com.example.notification

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.notification.api.NotificationAPI
import com.example.notification.model.Notification
import com.example.notification.model.NotificationData
import com.google.firebase.messaging.FirebaseMessaging
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.notification)

        button.setOnClickListener {
            sendNotification()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Dexter.withContext(applicationContext)
                .withPermission(Manifest.permission.POST_NOTIFICATIONS)
                .withListener(object : PermissionListener{
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {}

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {}

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {
                        p1?.continuePermissionRequest()
                    }
                }).check()
        }

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        FirebaseMessaging.getInstance().subscribeToTopic("test")

    }

    private fun sendNotification() {
        val notification = Notification(
            message = NotificationData(
                "topic",
                hashMapOf("title" to "This is a notification title",
                    "body" to "This is a body")
            )
        )
        NotificationAPI.sendNotification().notification(notification).enqueue(
            object : Callback<Notification> {
                override fun onResponse(p0: Call<Notification>, p1: Response<Notification>) {
                    Toast.makeText(this@MainActivity, "Notification sent.", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(p0: Call<Notification>, p1: Throwable) {
                    Toast.makeText(this@MainActivity, "Error: ${p1.message}", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }
}
