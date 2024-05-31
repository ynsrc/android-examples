package ynsrc.example.android.examples.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().height(80.dp)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Filled"
            )
        }

        OutlinedCard(
            modifier = Modifier.fillMaxWidth().height(80.dp)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Outlined"
            )
        }

        ElevatedCard(
            modifier = Modifier.fillMaxWidth().height(80.dp)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Elevated"
            )
        }
    }
}

@Preview
@Composable
private fun CardExamplesPreview() {
    CardExamples()
}