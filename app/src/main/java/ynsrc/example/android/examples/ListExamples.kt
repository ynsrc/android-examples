package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ListExamples() {
    Column {
        ListItem(
            headlineContent = {
                Text(text = "One line list item with 24x24 icon")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            }
        )

        HorizontalDivider()

        ListItem(
            headlineContent = { Text(text = "Headline Content") },
            supportingContent = { Text(text = "Supporting Content") },
            trailingContent = { Text(text = "Trailing") },
            leadingContent = { Text(text = "Leading") }
        )

    }
}

@Preview
@Composable
private fun ListExamplesPreview() {
    ListExamples()
}