package ynsrc.example.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ynsrc.example.android.ui.theme.AndroidExamplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainLayout()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val screenState: MutableState<Screen> = remember { mutableStateOf(Screen.HOME) }

    screenState.value.parent?.let { parentScreen ->
        BackHandler { screenState.value = parentScreen }
    }

    AndroidExamplesTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    navigationIcon = {
                        if (screenState.value != Screen.HOME) {
                            IconButton(
                                onClick = {
                                    screenState.value = screenState.value.parent ?: Screen.HOME
                                }
                            ) {
                                Icon(
                                    modifier = Modifier.padding(8.dp),
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        } else {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    modifier = Modifier.padding(8.dp),
                                    imageVector = Icons.Default.Home,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    val snackbarResult = snackbarHostState.showSnackbar(
                                        message = "This is an example action.",
                                        actionLabel = "Perform Action",
                                        duration = SnackbarDuration.Short
                                    )

                                    when (snackbarResult) {
                                        SnackbarResult.ActionPerformed -> {
                                            snackbarHostState.showSnackbar(
                                                message = "Action performed!",
                                                withDismissAction = true,
                                                duration = SnackbarDuration.Short
                                            )
                                        }

                                        SnackbarResult.Dismissed -> {
                                            snackbarHostState.showSnackbar(
                                                message = "Snackbar dismissed!",
                                                withDismissAction = true,
                                                duration = SnackbarDuration.Short
                                            )
                                        }
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null
                            )
                        }
                    }
                )
            },
        ) { insetPaddings ->
            screenState.value.content?.let { content ->
                Box(modifier = Modifier.fillMaxSize().padding(insetPaddings)) {
                    content()
                }
            } ?: LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(insetPaddings)
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val screens = when (screenState.value.parent) {
                    null -> Screen.entries.filter { it != Screen.HOME && it.parent == Screen.HOME }
                    else -> Screen.entries.filter { it.parent == screenState.value }
                }

                items(screens.size) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .clickable { screenState.value = screens[index] },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            modifier = Modifier.padding(6.dp),
                            imageVector = Icons.AutoMirrored.Default.ArrowForward,
                            contentDescription = null
                        )

                        Text(
                            modifier = Modifier.padding(vertical = 8.dp),
                            text = screens[index].title
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainLayout()
}