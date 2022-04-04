package dev.aungkyawpaing.notificationdismissdemo

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationManagerCompat

class NotificationActionReceiver : BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    Log.i("NotificationActionReceiver", "onReceive")
    NotificationManagerCompat.from(context)
      .cancel(intent.getIntExtra(Notification.EXTRA_NOTIFICATION_ID, 0))

    // Only close the notifications shade on Android versions where it is allowed, Android 11 and lower.
    // See https://developer.android.com/about/versions/12/behavior-changes-all#close-system-dialogs

    // Only close the notifications shade on Android versions where it is allowed, Android 11 and lower.
    // See https://developer.android.com/about/versions/12/behavior-changes-all#close-system-dialogs
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
      context.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
    }
  }
}