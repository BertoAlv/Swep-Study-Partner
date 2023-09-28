package com.alberto.studycompanion.detail.pomodoro.presentation.timer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import com.alberto.studycompanion.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PomodoroTimerViewModel @Inject constructor() : ViewModel() {

    fun onEvent(event: PomodoroTimerEvent) {

        when(event){

            is PomodoroTimerEvent.TimerEnded -> sendPomodoroNotification(event.context)

            is PomodoroTimerEvent.BreakEnded -> sendBreakEndedNotification(event.context)
        }

    }

    private fun sendPomodoroNotification(context: Context) {

        createNotificationChannel(context)

        val channelId = "studypartner_id"
        val notificationId = 1

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle("Pomodoro Finished!")
            .setContentText("Well done! Now take a deserved break.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVibrate(longArrayOf(0, 500, 100, 500)) // Vibration pattern (off, on, off, on)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId, notificationBuilder.build())

    }

    private fun sendBreakEndedNotification(context: Context) {

        createNotificationChannel(context)

        val channelId = "studypartner_id"
        val notificationId = 2

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle("Back to work.")
            .setContentText("The break time has ended. Let's get back to work.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVibrate(longArrayOf(0, 500, 100, 500)) // Vibration pattern (off, on, off, on)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId, notificationBuilder.build())

    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "studypartner_id"
            val channelName = "swep:studypartner"
            val channelDescription = "Notifications from SWEP : Study Partner app"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

}