package ynsrc.example.android.examples.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExtendedFABExamples() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        ExtendedFloatingActionButton(
            text = { Text(text = "Extended") },
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null) },
            onClick = {  }
        )

    }
}

@Preview
@Composable
private fun ExtendedFABExamplesPreview() {
    ExtendedFABExamples()
}