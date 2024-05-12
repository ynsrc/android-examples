package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetExamples() {
    Column {
        val scope = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }

        Button(onClick = {
            scope.launch { sheetState.show() }.invokeOnCompletion { showBottomSheet = true }
        }) {
            Text(text = "Show Bottom Sheet")
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(200.dp),
                    text = "Bottom Sheet Content"
                )
            }
        }
    }
}

@Preview
@Composable
private fun BottomSheetExamplesPreview() {
    BottomSheetExamples()
}