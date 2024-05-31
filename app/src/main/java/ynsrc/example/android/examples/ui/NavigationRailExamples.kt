package ynsrc.example.android.examples.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NavigationRailExamples() {
    val items = listOf("First", "Second", "Third", "Fourth")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star, Icons.Filled.Place)

    var selectedItem by remember { mutableStateOf(items[0]) }

    NavigationRail {
        items.forEachIndexed { index, item ->
            NavigationRailItem(
                selected = selectedItem == item,
                onClick = { selectedItem = item },
                label = { Text(text = item) },
                icon = {
                    Icon(imageVector = icons[index], contentDescription = null)
                }
            )
        }
    }
}

@Preview
@Composable
private fun NavigationRailExamplesPreview() {
    NavigationRailExamples()
}