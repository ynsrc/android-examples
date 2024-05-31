package ynsrc.example.android.examples.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarExamples() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        val navigationIcon = @Composable {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }

        val actions = @Composable {

            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }

            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null
                )
            }
        }

        TopAppBar(
            title = { Text(text = "Small") },
            navigationIcon = { navigationIcon() },
            actions = { actions() }
        )

        CenterAlignedTopAppBar(
            title = { Text(text = "Center Aligned") },
            navigationIcon = { navigationIcon() },
            actions = { actions() }
        )

        MediumTopAppBar(
            title = { Text(text = "Medium") },
            navigationIcon = { navigationIcon() },
            actions = { actions() }
        )

        LargeTopAppBar(
            title = { Text(text = "Large") },
            navigationIcon = { navigationIcon() },
            actions = { actions() }
        )
    }
}

@Preview
@Composable
private fun TopAppBarExamplesPreview() {
    TopAppBarExamples()
}