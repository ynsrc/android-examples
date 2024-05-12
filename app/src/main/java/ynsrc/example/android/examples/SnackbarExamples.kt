package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SnackbarExamples() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        Button(
            onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Hello, this is snackbar!",
                        actionLabel = "Action"
                    )
                }
            }
        ) {
            Text(text = "Show Snackbar")
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}

@Preview
@Composable
private fun SnackbarExamplesPreview() {
    SnackbarExamples()
}