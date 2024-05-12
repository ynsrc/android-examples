package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CommonButtonExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = {  }) { Text(text = " Filled ") }

        ElevatedButton(onClick = {  }) { Text(text = "Elevated") }

        FilledTonalButton(onClick = {  }) {  Text(text = "Filled Tonal") }

        OutlinedButton(onClick = {  }) {  Text(text = "Outlined") }

        TextButton(onClick = {  }) {  Text(text = "Text Button") }
    }
}

@Preview
@Composable
private fun CommonButtonExamplesPreview() {
    CommonButtonExamples()
}