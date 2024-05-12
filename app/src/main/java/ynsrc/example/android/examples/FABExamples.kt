package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FABExamples() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Text(text = "Small")

        SmallFloatingActionButton(onClick = {  }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }

        Text(text = "Normal")

        FloatingActionButton(onClick = {  }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }

        Text(text = "Large")

        LargeFloatingActionButton(onClick = {  }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }

    }
}

@Preview
@Composable
private fun FABExamplesPreview() {
    FABExamples()
}