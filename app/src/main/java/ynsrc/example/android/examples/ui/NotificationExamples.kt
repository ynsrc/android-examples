package ynsrc.example.android.examples.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.RingtoneManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationChannelGroupCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ynsrc.example.android.R
import ynsrc.example.android.utils.getActivity

@SuppressLint("InlinedApi")
@Composable
fun NotificationExamples() {
    val context = LocalContext.current
    val activity = context.getActivity()
    val scope = rememberCoroutineScope()
    val notificationManager = NotificationManagerCompat.from(context)

    var permissionIsGranted by remember {
        mutableStateOf(
            ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionIsGranted = isGranted
    }

    if (!permissionIsGranted) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }) {
                Text("Request Permission")
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val exampleChannelGroupId = "example-notification-group"
            val exampleChannelId = "example-notification-channel"
            val exampleProgressChannelId = "example-notification-channel-progress"

            notificationManager.createNotificationChannelGroup(
                NotificationChannelGroupCompat.Builder(exampleChannelGroupId)
                    .setName("Example Notification Group")
                    .setDescription("This is an example notification channel group.")
                    .build()
            )

            notificationManager.createNotificationChannel(
                NotificationChannelCompat.Builder(
                    exampleChannelId,
                    NotificationManagerCompat.IMPORTANCE_DEFAULT
                )
                    .setName("Example Notification Channel")
                    .setDescription("This is an example notification channel.")
                    .setShowBadge(true)
                    .setLightColor(Color.Blue.toArgb())
                    .setVibrationEnabled(false)
                    .setSound(
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .build()
                    )
                    .setGroup(exampleChannelGroupId)
                    .build()
            )

            notificationManager.createNotificationChannel(
                NotificationChannelCompat.Builder(
                    exampleProgressChannelId,
                    NotificationManagerCompat.IMPORTANCE_DEFAULT
                )
                    .setName("Example Progress Channel")
                    .setDescription("This is an example notification channel for progresses.")
                    .setShowBadge(true)
                    .setLightColor(Color.Blue.toArgb())
                    .setVibrationEnabled(false)
                    .setSound(null, null)
                    .setGroup(exampleChannelGroupId)
                    .build()
            )

            Button(onClick = {
                notificationManager.notify(
                    0,
                    NotificationCompat.Builder(context, exampleChannelId)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Simple Notification Example")
                        .setContentText("This is a simple notification.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build()
                )
            }) {
                Text("Show Simple Notification")
            }


            Button(onClick = {
                notificationManager.notify(
                    1,
                    NotificationCompat.Builder(context, exampleChannelId)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Expandable Notification Example")
                        .setContentText("Click here to expand content text.")
                        .setStyle(
                            NotificationCompat.BigTextStyle()
                                .bigText(
                                    """
                                    This is a very long notification text which will
                                    overflow in single line and when you touch on this notification
                                    this text will appear as the notification's content text.
                                    """.trimIndent().replace("\n", " ")
                                )
                        )
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build()
                )
            }) {
                Text("Show Expandable Notification")
            }

            Button(onClick = {
                notificationManager.notify(
                    2,
                    NotificationCompat.Builder(context, exampleChannelId)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Chronometer Example")
                        .setContentText("This is a simple notification with Chronometer.")
                        .setUsesChronometer(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build()
                )
            }) {
                Text("Show Notification with Chronometer")
            }

            Button(onClick = {
                scope.launch {
                    for (progress in 0..100) {
                        notificationManager.notify(
                            3,
                            NotificationCompat.Builder(context, exampleProgressChannelId)
                                .setSmallIcon(R.drawable.ic_launcher_foreground)
                                .setContentTitle("Progress Example")
                                .setContentText("Progress working in background. Please wait.")
                                .setSound(null)
                                .setVibrate(null)
                                .setOngoing(progress != 100)
                                .setProgress(100, progress, false)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .build()
                        )

                        delay(250)
                    }
                }
            }) {
                Text("Show Notification with Progress")
            }
        }
    }
}
