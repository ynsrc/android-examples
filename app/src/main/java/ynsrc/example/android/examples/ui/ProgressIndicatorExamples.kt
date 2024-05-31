package ynsrc.example.android.examples.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicatorExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "Linear")
        LinearProgressIndicator()

        Text(text = "Circular")
        CircularProgressIndicator()

        Text(text = "Linear with Value")
        LinearProgressIndicator(progress = { 0.3f })

        Text(text = "Circular with Value")
        CircularProgressIndicator(progress = { 0.3f })

    }
}

@Preview
@Composable
private fun ProgressIndicatorExamplesPreview() {
    ProgressIndicatorExamples()
}