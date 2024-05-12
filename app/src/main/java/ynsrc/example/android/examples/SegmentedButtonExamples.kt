package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtonExamples() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Text(text = "Single Choice Segmented Button Row")

        var selectedIndex by remember { mutableIntStateOf(0) }
        val options = listOf("Day", "Month", "Year")
        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = { selectedIndex = index },
                    selected = index == selectedIndex
                ) {
                    Text(label)
                }
            }
        }

        Text(text = "Multi Choice Segmented Button Row")

        val checkableOptions = listOf("Favorites", "Trending", "Saved")
        val checkedList = remember { mutableStateListOf<Int>() }
        val icons = listOf(Icons.Filled.Favorite, Icons.Filled.Star, Icons.Filled.Place)
        MultiChoiceSegmentedButtonRow {
            checkableOptions.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    icon = {
                        SegmentedButtonDefaults.Icon(active = index in checkedList) {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = null,
                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                            )
                        }
                    },
                    onCheckedChange = {
                        if (index in checkedList) {
                            checkedList.remove(index)
                        } else {
                            checkedList.add(index)
                        }
                    },
                    checked = index in checkedList
                ) {
                    Text(label)
                }
            }
        }
    }



}

@Preview
@Composable
private fun SegmentedButtonExamplesPreview() {
    SegmentedButtonExamples()
}