package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        val sliderState = remember { SliderState(value = 0.3f) }

        Slider(
            modifier = Modifier.fillMaxWidth(),
            state = sliderState
        )

        Text(text = "Slider value: ${sliderState.value}")

        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = { sliderState.value }
        )

        CircularProgressIndicator(progress = { sliderState.value })

    }
}

@Preview
@Composable
private fun SliderExamplesPreview() {
    SliderExamples()
}