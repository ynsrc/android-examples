package ynsrc.example.android.examples.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DividerExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "Horizontal Divider")

        HorizontalDivider()

        Text(text = "Between This")

        HorizontalDivider(thickness = 3.dp, color = MaterialTheme.colorScheme.primary)

        Text(text = "And This Text")
    }
}

@Preview
@Composable
private fun DividerExamplesPreview() {
    DividerExamples()
}