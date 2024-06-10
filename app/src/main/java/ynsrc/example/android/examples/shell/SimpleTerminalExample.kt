package ynsrc.example.android.examples.shell

import android.view.WindowManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import kotlinx.coroutines.launch
import ynsrc.example.android.utils.getActivity

@Composable
fun SimpleTerminalExample() {
    val scope = rememberCoroutineScope()
    val terminalOutput = remember {
        mutableStateListOf(
            Pair("Welcome!", "You can try:\nuname -a\nuptime\nclear\npm\nam\nenv\netc."),
            Pair("Warning!", "Don't try here /bin/sh or /system/bin/sh\nThey will cause not responding!")
        )
    }

    LocalContext.current.getActivity()?.window?.setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val listState = rememberLazyListState()

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            state = listState
        ) {
            terminalOutput.forEach { (key, value) ->
                item {
                    ListItem(
                        headlineContent = {
                            Text(
                                text = key,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        supportingContent = { Text(value) }
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var inputText by remember { mutableStateOf("") }

            fun executeCommand() {
                runCatching {
                    val process = Runtime.getRuntime().exec(inputText)
                    process.inputStream?.bufferedReader()?.readText()?.let {
                        val response = it.trim()
                        if (response.startsWith("\u001B[2J")) {
                            terminalOutput.clear()
                        } else {
                            terminalOutput.add(Pair(inputText, it.trim()))
                        }
                    }
                }.onFailure { throwable ->
                    terminalOutput.add(Pair(inputText, throwable.message ?: "error"))
                }
                inputText = ""

                if (terminalOutput.size > 0) {
                    scope.launch {
                        listState.scrollToItem(terminalOutput.size - 1, 0)
                    }
                }
            }

            TextField(
                modifier = Modifier.weight(1f),
                value = inputText,
                placeholder = { Text("command") },
                onValueChange = { inputText = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = { executeCommand() }),
                trailingIcon = {
                    IconButton(
                        onClick = { executeCommand() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.Send,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}
