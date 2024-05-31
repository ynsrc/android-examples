package ynsrc.example.android.examples.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomAppBarExamples() {
    BottomAppBar(
        actions = {
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }

            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }

            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
            }

            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    )
}

@Preview
@Composable
private fun BottomAppBarExamplesPreview() {
    BottomAppBarExamples()
}