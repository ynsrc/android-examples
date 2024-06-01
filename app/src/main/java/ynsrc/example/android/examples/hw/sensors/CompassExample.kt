package ynsrc.example.android.examples.hw.sensors

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import ynsrc.example.android.utils.getActivity

@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun CompassExample() {
    val context = LocalContext.current
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    var degrees by remember { mutableFloatStateOf(0.0f) }
    val sensorListener = object : SensorEventListener {
        var mGravity = FloatArray(0)
        var mGeomagnetic = FloatArray(0)

        override fun onSensorChanged(event: SensorEvent?) {
            event?.let { e ->
                when (e.sensor.type) {
                    Sensor.TYPE_MAGNETIC_FIELD -> mGravity = e.values
                    Sensor.TYPE_ACCELEROMETER -> mGeomagnetic = e.values
                }
            }

            if (mGravity.isNotEmpty() && mGeomagnetic.isNotEmpty()) {
                val R = FloatArray(9)
                val I = FloatArray(9)
                if (SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic)) {
                    val orientation = FloatArray(3)
                    SensorManager.getOrientation(R, orientation)
                    degrees = Math.toDegrees(orientation[0].toDouble()).toFloat()
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier.rotate(degrees),
            text = "%.2f °".format(degrees + 180),
            style = MaterialTheme.typography.titleLarge
        )
    }

    LaunchedEffect(Unit) {
        context.getActivity()?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        sensorManager.registerListener(
            sensorListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
            SensorManager.SENSOR_DELAY_UI
        )

        sensorManager.registerListener(
            sensorListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI
        )
    }

    DisposableEffect(Unit) {
        context.getActivity()?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        onDispose {
            runCatching {
                sensorManager.unregisterListener(sensorListener)
            }
        }
    }
}