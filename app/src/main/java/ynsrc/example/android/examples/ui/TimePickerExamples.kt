package ynsrc.example.android.examples.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TimePicker(
            modifier = Modifier.fillMaxWidth(),
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