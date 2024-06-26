package ynsrc.example.android

import androidx.compose.runtime.Composable
import ynsrc.example.android.examples.hw.camera.CameraExample
import ynsrc.example.android.examples.hw.microphone.MicrophoneExample
import ynsrc.example.android.examples.hw.nfc.NfcExample
import ynsrc.example.android.examples.hw.sensors.CompassExample
import ynsrc.example.android.examples.hw.sensors.SensorExamples
import ynsrc.example.android.examples.shell.SimpleTerminalExample
import ynsrc.example.android.examples.sysinfo.BatteryInformationExample
import ynsrc.example.android.examples.sysinfo.BuildInformationExample
import ynsrc.example.android.examples.sysinfo.EnvironmentVariablesExample
import ynsrc.example.android.examples.ui.BadgeExamples
import ynsrc.example.android.examples.ui.BottomAppBarExamples
import ynsrc.example.android.examples.ui.BottomSheetExamples
import ynsrc.example.android.examples.ui.CardExamples
import ynsrc.example.android.examples.ui.CheckboxExamples
import ynsrc.example.android.examples.ui.ChipExamples
import ynsrc.example.android.examples.ui.CommonButtonExamples
import ynsrc.example.android.examples.ui.DatePickerExamples
import ynsrc.example.android.examples.ui.DialogExamples
import ynsrc.example.android.examples.ui.DividerExamples
import ynsrc.example.android.examples.ui.ExtendedFABExamples
import ynsrc.example.android.examples.ui.FABExamples
import ynsrc.example.android.examples.ui.IconButtonExamples
import ynsrc.example.android.examples.ui.ListExamples
import ynsrc.example.android.examples.ui.MenuExamples
import ynsrc.example.android.examples.ui.NavigationBarExamples
import ynsrc.example.android.examples.ui.NavigationDrawerExamples
import ynsrc.example.android.examples.ui.NavigationRailExamples
import ynsrc.example.android.examples.ui.NotificationExamples
import ynsrc.example.android.examples.ui.ProgressIndicatorExamples
import ynsrc.example.android.examples.ui.RadioButtonExamples
import ynsrc.example.android.examples.ui.SearchExamples
import ynsrc.example.android.examples.ui.SegmentedButtonExamples
import ynsrc.example.android.examples.ui.SliderExamples
import ynsrc.example.android.examples.ui.SnackbarExamples
import ynsrc.example.android.examples.ui.SwitchExamples
import ynsrc.example.android.examples.ui.TabExamples
import ynsrc.example.android.examples.ui.TextFieldExamples
import ynsrc.example.android.examples.ui.TimePickerExamples
import ynsrc.example.android.examples.ui.TopAppBarExamples

enum class Screen(
    val title: String,
    val parent: Screen? = null,
    val content: (@Composable () -> Unit)? = null
) {
    HOME(title = "HOME"),

    UI(title = "USER INTERFACE (UI)", parent = HOME),

    APP_BARS(title = "APP BARS", parent = UI),
    BOTTOM_APP_BAR(
        title = "BOTTOM APP BAR",
        parent = APP_BARS,
        content = { BottomAppBarExamples() }),
    TOP_APP_BAR(title = "TOP APP BAR", parent = APP_BARS, content = { TopAppBarExamples() }),

    BADGES(title = "BADGES", parent = UI, content = { BadgeExamples() }),

    BUTTONS(title = "BUTTONS", parent = UI),
    COMMON_BUTTONS(
        title = "COMMON BUTTONS",
        parent = BUTTONS,
        content = { CommonButtonExamples() }),
    FAB(title = "FAB", parent = BUTTONS, content = { FABExamples() }),
    EXTENDED_FAB(title = "EXTENDED FAB", parent = BUTTONS, content = { ExtendedFABExamples() }),
    ICON_BUTTONS(title = "ICON BUTTONS", parent = BUTTONS, content = { IconButtonExamples() }),
    SEGMENTED_BUTTONS(
        title = "SEGMENTED BUTTONS",
        parent = BUTTONS,
        content = { SegmentedButtonExamples() }),

    CARDS(title = "CARDS", parent = UI, content = { CardExamples() }),
    CHECKBOX(title = "CHECKBOX", parent = UI, content = { CheckboxExamples() }),
    CHIPS(title = "CHIPS", parent = UI, content = { ChipExamples() }),
    DATE_PICKERS(title = "DATE PICKERS", parent = UI, content = { DatePickerExamples() }),
    DIALOGS(title = "DIALOGS", parent = UI, content = { DialogExamples() }),
    DIVIDER(title = "DIVIDER", parent = UI, content = { DividerExamples() }),
    LISTS(title = "LISTS", parent = UI, content = { ListExamples() }),
    MENUS(title = "MENUS", parent = UI, content = { MenuExamples() }),

    NAVIGATION(title = "NAVIGATION", parent = UI),
    NAVIGATION_BAR(
        title = "NAVIGATION BAR",
        parent = NAVIGATION,
        content = { NavigationBarExamples() }),
    NAVIGATION_DRAWER(
        title = "NAVIGATION DRAWER",
        parent = NAVIGATION,
        content = { NavigationDrawerExamples() }),
    NAVIGATION_RAIL(
        title = "NAVIGATION RAIL",
        parent = NAVIGATION,
        content = { NavigationRailExamples() }),

    NOTIFICATION(title = "NOTIFICATION", parent = UI, content = { NotificationExamples() }),

    PROGRESS_INDICATORS(
        title = "PROGRESS INDICATORS",
        parent = UI,
        content = { ProgressIndicatorExamples() }),
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

    HARDWARE(title = "HARDWARE (HW)", parent = HOME),

    CAMERA(title = "CAMERA", parent = HARDWARE, content = { CameraExample() }),

    MICROPHONE(title = "MICROPHONE", parent = HARDWARE, content = { MicrophoneExample() }),

    SENSORS(title = "SENSORS", parent = HARDWARE),

    ALL_SENSORS(title = "ALL SENSORS", parent = SENSORS, content = { SensorExamples() }),

    COMPASS(title = "COMPASS", parent = SENSORS, content = { CompassExample() }),

    NFC(title = "NFC", parent = HARDWARE, content = { NfcExample() }),

    SYSTEM_INFORMATION(title = "SYSTEM INFORMATION", parent = HOME),

    BUILD_INFORMATION(
        title = "BUILD INFORMATION",
        parent = SYSTEM_INFORMATION,
        content = { BuildInformationExample() }),

    BATTERY_INFORMATION(
        title = "BATTERY INFORMATION",
        parent = SYSTEM_INFORMATION,
        content = { BatteryInformationExample() }),

    ENVIRONMENT_VARIABLES(
        title = "ENVIRONMENT VARIABLES",
        parent = SYSTEM_INFORMATION,
        content = { EnvironmentVariablesExample() }),

    SHELL(title = "SHELL", parent = HOME),

    TERMINAL(title = "SIMPLE TERMINAL", parent = SHELL, content = { SimpleTerminalExample() }),
}
