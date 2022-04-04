package dev.aungkyawpaing.notificationdismissdemo

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    val channel = NotificationChannel(
      "test_channel",
      "Test Channel",
      NotificationManager.IMPORTANCE_DEFAULT
    ).apply {
      description = "Description"
    }
    // Register the channel with the system
    val notificationManager: NotificationManager =
      getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)


    findViewById<Button>(R.id.btn_show_notification).setOnClickListener {
      val actionIntent = Intent(this, NotificationActionReceiver::class.java).apply {
        putExtra(EXTRA_NOTIFICATION_ID, 0)
      }
      val actionPendingIntent: PendingIntent =
        PendingIntent.getBroadcast(this, 0, actionIntent, FLAG_IMMUTABLE)

      val notification = NotificationCompat.Builder(this, "test_channel")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Test Title")
        .setContentText("Loreum Ipsum")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .addAction(R.drawable.ic_launcher_foreground, "Action", actionPendingIntent)
        .build()
      notificationManager.notify(0, notification)
    }
  }
}