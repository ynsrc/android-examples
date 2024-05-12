package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DialogExamples() {
    var showAlertDialog by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Button(onClick = { showAlertDialog = true }) {
            Text(text = "Show Alert")
        }
    }

    if (showAlertDialog) {
        AlertDialog(
            title = { Text(text = "Alert Dialog") },
            text = { Text(text = "This is a simple alert dialog.") },
            onDismissRequest = { showAlertDialog = false },
            dismissButton = {
                TextButton(onClick = { showAlertDialog = false }) {
                    Text(text = "Dismiss")
                }
            },
            confirmButton = {
                TextButton(onClick = { showAlertDialog = false }) {
                    Text(text = "Confirm")
                }
            }
        )
    }

}

@Preview
@Composable
private fun DialogExamplesPreview() {
    DialogExamples()
}