package ynsrc.example.android.examples.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavigationBarExamples() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        val items = listOf("First", "Second", "Third", "Fourth")
        val icons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star, Icons.Filled.Place)
        
        var selectedItem by remember { mutableStateOf(items[0]) }

        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
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
}

@Preview
@Composable
private fun NavigationBarExamplesPreview() {
    NavigationBarExamples()
}