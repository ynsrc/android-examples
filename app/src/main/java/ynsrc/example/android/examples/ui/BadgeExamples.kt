package ynsrc.example.android.examples.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BadgeExamples() {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Box {
                    Badge(modifier = Modifier.align(Alignment.TopEnd)) {
                        Text(text = "12")
                    }

                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun BadgeExamplesPreview() {
    BadgeExamples()
}