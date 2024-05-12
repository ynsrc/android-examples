package ynsrc.example.android.examples

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TabExamples() {
    var tabs = listOf("First", "Second", "Third")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    TabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index }
            ) {
                Text(text = tabs[index])
            }
        }
    }
}

@Preview
@Composable
private fun TabExamplesPreview() {
    TabExamples()
}