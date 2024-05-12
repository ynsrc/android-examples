package ynsrc.example.android.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerExamples() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Text(text = "Input")

        DatePicker(
            state = rememberDatePickerState(
                initialDisplayMode = DisplayMode.Input,
                yearRange = IntRange(2023, 2025)
            )
        )

        Text(text = "Picker")

        DatePicker(
            state = rememberDatePickerState(
                selectableDates = object : SelectableDates {

                    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                        return true
                    }

                    override fun isSelectableYear(year: Int): Boolean {
                        return year in IntRange(1990, 2030)
                    }

                }
            )
        )

    }
}

@Preview
@Composable
private fun DatePickerExamplesPreview() {
    DatePickerExamples()
}