package ynsrc.example.android.examples.hw.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SensorExamples() {
    val context = LocalContext.current
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    var selectedSensor: Sensor? by remember { mutableStateOf(null) }

    if (selectedSensor == null) {
        LazyColumn {
            sensorManager.getSensorList(Sensor.TYPE_ALL).forEach { sensor ->
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedSensor = sensor }
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = sensor.name
                        )
                    }
                }
            }
        }
    } else selectedSensor?.let { sensor ->
        var accuracyValue by remember { mutableIntStateOf(0) }
        val sensorValues  = remember { mutableStateListOf<Float>() }
        val sensorEventListener = remember {
            object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    event?.values?.let { newValues ->
                        sensorValues.clear()
                        sensorValues.addAll(newValues.toList())
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                    accuracyValue = accuracy
                }
            }
        }

        LaunchedEffect(Unit) {
            sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                runCatching { sensorManager.unregisterListener(sensorEventListener) }
            }
        }

        BackHandler { selectedSensor = null }

        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { selectedSensor = null }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }

            Text("Sensor: ${sensor.name}")
            Text("Accuracy: $accuracyValue")
            Text("Values (${sensorValues.size}): ")
            sensorValues.forEachIndexed { index, fl ->
                Text("[$index] = $fl")
            }
        }
    }
}
