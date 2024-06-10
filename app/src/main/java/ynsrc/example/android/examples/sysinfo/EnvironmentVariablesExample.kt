package ynsrc.example.android.examples.sysinfo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun EnvironmentVariablesExample() {
    LazyColumn {
        Runtime.getRuntime().exec("env").inputStream
            ?.bufferedReader()?.readLines()?.forEach {
                item {
                    Text(it)
                }
            }
    }
}