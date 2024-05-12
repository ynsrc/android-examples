package ynsrc.example.android

import androidx.compose.runtime.Composable
import ynsrc.example.android.examples.BadgeExamples
import ynsrc.example.android.examples.BottomAppBarExamples
import ynsrc.example.android.examples.BottomSheetExamples
import ynsrc.example.android.examples.CardExamples
import ynsrc.example.android.examples.CheckboxExamples
import ynsrc.example.android.examples.ChipExamples
import ynsrc.example.android.examples.CommonButtonExamples
import ynsrc.example.android.examples.DatePickerExamples
import ynsrc.example.android.examples.DialogExamples
import ynsrc.example.android.examples.DividerExamples
import ynsrc.example.android.examples.ExtendedFABExamples
import ynsrc.example.android.examples.FABExamples
import ynsrc.example.android.examples.IconButtonExamples
import ynsrc.example.android.examples.ListExamples
import ynsrc.example.android.examples.MenuExamples
import ynsrc.example.android.examples.NavigationBarExamples
import ynsrc.example.android.examples.NavigationDrawerExamples
import ynsrc.example.android.examples.NavigationRailExamples
import ynsrc.example.android.examples.ProgressIndicatorExamples
import ynsrc.example.android.examples.RadioButtonExamples
import ynsrc.example.android.examples.SearchExamples
import ynsrc.example.android.examples.SegmentedButtonExamples
import ynsrc.example.android.examples.SliderExamples
import ynsrc.example.android.examples.SnackbarExamples
import ynsrc.example.android.examples.SwitchExamples
import ynsrc.example.android.examples.TabExamples
import ynsrc.example.android.examples.TextFieldExamples
import ynsrc.example.android.examples.TimePickerExamples
import ynsrc.example.android.examples.TopAppBarExamples

enum class Screen(
    val title: String,
    val parent: Screen? = null,
    val content: (@Composable ()->Unit)? = null
) {
    HOME(title = "HOME"),

    UI(title = "USER INTERFACE (UI)", parent = HOME),

    APP_BARS(title = "APP BARS", parent = UI),
    BOTTOM_APP_BAR(title = "BOTTOM APP BAR", parent = APP_BARS, content = { BottomAppBarExamples() }),
    TOP_APP_BAR(title = "TOP APP BAR", parent = APP_BARS, content = { TopAppBarExamples() }),

    BADGES(title = "BADGES", parent = UI, content = { BadgeExamples() }),

    BUTTONS(title = "BUTTONS", parent = UI),
    COMMON_BUTTONS(title = "COMMON BUTTONS", parent = BUTTONS, content = { CommonButtonExamples() }),
    FAB(title = "FAB", parent = BUTTONS, content = { FABExamples() }),
    EXTENDED_FAB(title = "EXTENDED FAB", parent = BUTTONS, content = { ExtendedFABExamples() }),
    ICON_BUTTONS(title = "ICON BUTTONS", parent = BUTTONS, content = { IconButtonExamples() }),
    SEGMENTED_BUTTONS(title = "SEGMENTED BUTTONS", parent = BUTTONS, content = { SegmentedButtonExamples() }),

    CARDS(title = "CARDS", parent = UI, content = { CardExamples() }),
    CHECKBOX(title = "CHECKBOX", parent = UI, content = { CheckboxExamples() }),
    CHIPS(title = "CHIPS", parent = UI, content = { ChipExamples() }),
    DATE_PICKERS(title = "DATE PICKERS", parent = UI, content = { DatePickerExamples() }),
    DIALOGS(title = "DIALOGS", parent = UI, content = { DialogExamples() }),
    DIVIDER(title = "DIVIDER", parent = UI, content = { DividerExamples() }),
    LISTS(title = "LISTS", parent = UI, content = { ListExamples() }),
    MENUS(title = "MENUS", parent = UI, content = { MenuExamples() }),

    NAVIGATION(title = "NAVIGATION", parent = UI),
    NAVIGATION_BAR(title = "NAVIGATION BAR", parent = NAVIGATION, content = { NavigationBarExamples() }),
    NAVIGATION_DRAWER(title = "NAVIGATION DRAWER", parent = NAVIGATION, content = { NavigationDrawerExamples() }),
    NAVIGATION_RAIL(title = "NAVIGATION RAIL", parent = NAVIGATION, content = { NavigationRailExamples() }),

    PROGRESS_INDICATORS(title = "PROGRESS INDICATORS", parent = UI, content = { ProgressIndicatorExamples() }),
    RADIO_BUTTON(title = "RADIO BUTTON", parent = UI, content = { RadioButtonExamples() }),
    SEARCH(title = "SEARCH", parent = UI, content = { SearchExamples() }),

    SHEETS(title = "SHEETS", parent = UI),
    BOTTOM_SHEETS(title = "BOTTOM SHEETS", parent = SHEETS, content = { BottomSheetExamples() }),

    SLIDERS(title = "SLIDERS", parent = UI, content = { SliderExamples() }),
    SNACKBAR(title = "SNACKBAR", parent = UI, content = { SnackbarExamples() }),
    SWITCH(title = "SWITCH", parent = UI, content = { SwitchExamples() }),
    TABS(title = "TABS", parent = UI, content = { TabExamples() }),
    TEXT_FIELDS(title = "TEXT FIELDS", parent = UI, content = { TextFieldExamples() }),
    TIME_PICKERS(title = "TIME PICKERS", parent = UI, content = { TimePickerExamples() }),
}
