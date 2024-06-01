package ynsrc.example.android.examples.sysinfo

import android.os.Build
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BuildInformationExample() {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        Build::class.java.fields.forEach { field ->
            item {
                ListItem(
                    headlineContent = { Text(field.name) },
                    supportingContent = {
                        Text(
                            when (field.type.simpleName) {
                                "String[]" -> (field.get(null) as Array<*>).joinToString(", ")
                                else -> "${field.get(null)}"
                            }
                        )
                    },
                    trailingContent = { Text(field.type.simpleName) }
                )
            }
        }
    }
}

@Preview
@Composable
fun SystemInformationExamplePreview() {
    BuildInformationExample()
}