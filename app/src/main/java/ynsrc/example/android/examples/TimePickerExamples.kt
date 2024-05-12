package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerExamples() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        TimePicker(
            state = rememberTimePickerState(
                initialHour = 12,
                initialMinute = 30
            )
        )

    }
}

@Preview
@Composable
private fun TimePickerExamplesPreview() {
    TimePickerExamples()
}