package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerExamples() {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val items = listOf("First", "Second", "Third", "Fourth")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star, Icons.Filled.Place)

    var selectedItem by remember { mutableStateOf(items[0]) }

    Button(onClick = { scope.launch { drawerState.open() } }) {
        Text(text = "Show Navigation Drawer")
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { ModalDrawerSheet {
            Text(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp, bottom = 8.dp),
                text = "Modal Drawer Sheet",
                style = MaterialTheme.typography.titleLarge
            )

            HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))

            items.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    selected = selectedItem == item,
                    onClick = { selectedItem = item },
                    label = { Text(text = item) },
                    icon = {
                        Icon(imageVector = icons[index], contentDescription = null)
                    }
                )
            }
        } }
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Content")
        }
    }
}

@Preview
@Composable
private fun NavigationDrawerExamplesPreview() {
    NavigationDrawerExamples()
}