package ynsrc.example.android.examples.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChipExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AssistChip(
            onClick = {  },
            label = { Text(text = "Assist Chip") }
        )

        AssistChip(
            onClick = {  },
            label = { Text(text = "With Leading Icon") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        )

        AssistChip(
            onClick = {  },
            label = { Text(text = "With Trailing Icon") },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        )

        AssistChip(
            onClick = {  },
            label = { Text(text = "With Two Icons") },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        )

        var selected by remember { mutableStateOf(false) }

        FilterChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text(text = "Filter Chip") },
            leadingIcon = {
                Icon(
                    imageVector = when {
                        selected -> Icons.Default.Check
                        else -> Icons.Default.Clear
                    },
                    contentDescription = null
                )
            }
        )

        var selected2 by remember { mutableStateOf(false) }

        InputChip(
            selected = selected2,
            onClick = { selected2 = !selected2 },
            label = { Text(text = "Input Chip") },
            avatar = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            }
        )

    }
}

@Preview
@Composable
private fun ChipExamplesPreview() {
    ChipExamples()
}