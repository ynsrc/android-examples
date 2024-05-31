package ynsrc.example.android.examples.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchExamples() {
    var searchQuery by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }

    Column {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = { },
            active = isActive,
            onActiveChange = { isActive = it },
            placeholder = { Text(text = "Type to search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (isActive) {
                    IconButton(
                        onClick = {
                            isActive = false
                            searchQuery = ""
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                } else {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                }
            }
        ) {
            if (searchQuery.isNotEmpty()) {
                Text(text = "Search Results for $searchQuery")
            }
        }
    }
}

@Preview
@Composable
private fun SearchExamplesPreview() {
    SearchExamples()
}