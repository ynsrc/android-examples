package ynsrc.example.android.examples.sysinfo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

data class BatteryInfo(
    var health: Int = BatteryManager.BATTERY_HEALTH_UNKNOWN,
    var status: Int = BatteryManager.BATTERY_STATUS_UNKNOWN,
    var level: Int = 0,
    var scale: Int = 100,
    var plugged: Int = 0,
    var present: Boolean = true,
    var technology: String = "UNKNOWN",
    var voltage: Int = 0,
    var temperature: Int = 0,
    var isCharging: Boolean? = null,
    var computedChargeTimeRemaining: Long? = null,
    var capacity: Int = 0,
    var chargeCounter: Int = 0,
    var currentNow: Int = 0,
    var currentAverage: Int = 0,
    var energyCounter: Long = 0L
) {
    constructor(batteryManager: BatteryManager, intent: Intent?) : this() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isCharging = batteryManager.isCharging
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            status = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            computedChargeTimeRemaining = batteryManager.computeChargeTimeRemaining()
        }

        capacity = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        chargeCounter = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER)
        currentNow = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)
        currentAverage = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE)
        energyCounter = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER)

        intent?.let {
            health = it.getIntExtra(BatteryManager.EXTRA_HEALTH, health)
            level = it.getIntExtra(BatteryManager.EXTRA_LEVEL, level)
            scale = it.getIntExtra(BatteryManager.EXTRA_SCALE, scale)
            plugged = it.getIntExtra(BatteryManager.EXTRA_PLUGGED, plugged)
            present = it.getBooleanExtra(BatteryManager.EXTRA_PRESENT, present)
            technology = it.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY) ?: technology
            voltage = it.getIntExtra(BatteryManager.EXTRA_VOLTAGE, voltage)
            temperature = it.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, temperature)
        }
    }
}

@Composable
fun BatteryInformationExample() {
    val context = LocalContext.current
    val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    var info by remember { mutableStateOf(BatteryInfo(batteryManager, null)) }

    val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            info = BatteryInfo(batteryManager, intent)
        }
    }

    LaunchedEffect(Unit) {
        context.registerReceiver(
            broadcastReceiver,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            context.unregisterReceiver(broadcastReceiver)
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        info.isCharging?.let { isCharging ->
            item { Text("Charging: $isCharging") }
        }

        item { Text("Present: ${info.present}") }

        item {
            Text(
                "Status: " + when (info.status) {
                    BatteryManager.BATTERY_STATUS_CHARGING -> "CHARGING"
                    BatteryManager.BATTERY_STATUS_DISCHARGING -> "DISCHARGING"
                    BatteryManager.BATTERY_STATUS_FULL -> "FULL"
                    BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "NOT CHARGING"
                    else -> "UNKNOWN"
                }
            )
        }

        item {
            Text("Health: " + when (info.health) {
                BatteryManager.BATTERY_HEALTH_GOOD -> "GOOD"
                BatteryManager.BATTERY_HEALTH_COLD -> "COLD"
                BatteryManager.BATTERY_HEALTH_DEAD -> "DEAD"
                BatteryManager.BATTERY_HEALTH_OVERHEAT -> "OVERHEAT"
                BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "OVER VOLTAGE"
                BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "UNSPECIFIED FAILURE"
                else -> "UNKNOWN"
            })
        }

        item {
            Text("Plugged: " + when (info.plugged) {
                BatteryManager.BATTERY_PLUGGED_AC -> "AC"
                BatteryManager.BATTERY_PLUGGED_USB -> "USB"
                BatteryManager.BATTERY_PLUGGED_DOCK -> "DOCK"
                BatteryManager.BATTERY_PLUGGED_WIRELESS -> "WIRELES"
                else -> "UNKNOWN"
            })
        }

        item { Text("Level: ${info.level} / ${info.scale}") }
        item { Text("Capacity: ${info.capacity}%") }
        item { Text("Capacity (uA.h): ${info.chargeCounter}") }
        item { Text("Current Now (uA): ${info.currentNow}") }
        item { Text("Current Avg. (uA): ${info.currentAverage}") }
        item { Text("Voltage: ${info.voltage}") }
        item { Text("Temperature: ${info.temperature} °K (${info.temperature - 273} °C)") }

        item { Text("Remaining Energy (nW.h): ${info.energyCounter}") }

        info.computedChargeTimeRemaining?.let { computedChargeTimeRemaining ->
            item { Text("Approx. Charge Time (ms): $computedChargeTimeRemaining") }
        }
    }
}