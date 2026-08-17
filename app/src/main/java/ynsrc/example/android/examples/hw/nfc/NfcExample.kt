package ynsrc.example.android.examples.hw.nfc

import android.content.Context
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.nfc.tech.MifareClassic
import android.nfc.tech.Ndef
import android.nfc.tech.NfcA
import android.nfc.tech.NfcB
import android.nfc.tech.NfcF
import android.nfc.tech.NfcV
import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ynsrc.example.android.utils.getActivity

@Composable
fun NfcExample() {
    val context = LocalContext.current
    val nfcManager = context.getSystemService(Context.NFC_SERVICE) as NfcManager
    val nfcAdapter = nfcManager.defaultAdapter

    if (nfcAdapter == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Your device does not support NFC!")
        }
    } else {
        val tagList = remember { mutableStateListOf<Tag>() }

        LaunchedEffect(Unit) {
            nfcAdapter.enableReaderMode(
                context.getActivity(), { tag ->
                    tagList.add(tag)
                }, NfcAdapter.FLAG_READER_NFC_A
                        or NfcAdapter.FLAG_READER_NFC_B
                        or NfcAdapter.FLAG_READER_NFC_F
                        or NfcAdapter.FLAG_READER_NFC_V
                        or NfcAdapter.FLAG_READER_NFC_BARCODE
                        or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
                null
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                nfcAdapter.disableReaderMode(context.getActivity())
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(8.dp)
        ) {
            item { Text("Enabled: ${nfcAdapter.isEnabled}") }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                item {
                    Text("SecureNfcSupported: ${nfcAdapter.isSecureNfcSupported}")
                    Text("SecureNfcEnabled: ${nfcAdapter.isSecureNfcEnabled}")
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                nfcAdapter.nfcAntennaInfo?.let { antennaInfo ->
                    item { Text("Device Width: ${antennaInfo.deviceWidth}") }
                    item { Text("Device Height: ${antennaInfo.deviceHeight}") }
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.CINNAMON_BUN) {
                        @Suppress("DEPRECATION")
                        item { Text("IsDeviceFoldable: ${antennaInfo.isDeviceFoldable}") }
                    }
                    antennaInfo.availableNfcAntennas.forEachIndexed { index, antenna ->
                        item { Text("Antenna $index located at (${antenna.locationX}, ${antenna.locationY})") }
                    }
                }
            }

            item { Text("Read Tags:") }

            tagList.forEach { tag ->
                item { NfcExampleTag(tag) }
            }
        }
    }
}

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun NfcExampleTag(tag: Tag) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text("Tag Id: ${tag.id.toHexString()}")
        tag.techList.forEach { tech ->
            when (tech) {
                "android.nfc.tech.IsoDep" -> {
                    IsoDep.get(tag)?.let { isoDep ->
                        Text("IsoDep: ")
                        Text("-Extended Length Apdu Supported: ${isoDep.isExtendedLengthApduSupported}")
                        Text("-Historical Bytes: ${isoDep.historicalBytes.toHexString()}")
                        Text("-Hi Layer Response: ${isoDep.hiLayerResponse.toHexString()}")
                    }
                }

                "android.nfc.tech.NfcA" -> {
                    NfcA.get(tag)?.let { nfcA ->
                        Text("NfcA: ")
                        Text("-SAK/SEL_RES: ${nfcA.sak}")
                        Text("-ATQA/SENS_RES: ${nfcA.atqa.toHexString()}")
                    }
                }

                "android.nfc.tech.NfcB" -> {
                    NfcB.get(tag)?.let { nfcB ->
                        Text("NfcB: ")
                        Text("-Protocol Info: ${nfcB.protocolInfo.toHexString()}")
                        Text("-Application Data: ${nfcB.applicationData.toHexString()}")
                    }
                }

                "android.nfc.tech.NfcF" -> {
                    NfcF.get(tag)?.let { nfcF ->
                        Text("NfcF: ")
                        Text("-SystemCode: ${nfcF.systemCode.toHexString()}")
                        Text("-Manufacturer: ${nfcF.manufacturer.toHexString()}")
                    }
                }

                "android.nfc.tech.NfcV" -> {
                    NfcV.get(tag)?.let { nfcV ->
                        Text("NfcV: ")
                        Text("-DSF ID: ${nfcV.dsfId}")
                        Text("-Response Flags: ${nfcV.responseFlags}")
                    }
                }

                "android.nfc.tech.Ndef" -> {
                    Ndef.get(tag)?.let { nDef ->
                        Text("Ndef: ")
                        Text("Type: ${nDef.type}")
                        Text("Connected: ${nDef.isConnected}")
                        Text("Writable: ${nDef.isWritable}")
                        nDef.cachedNdefMessage?.records?.forEach { record ->
                            Text("Cached Record #${record.id}")
                            Text("-TNF: ${record.tnf}")
                            Text("-Type: ${record.type.toHexString()}")
                            Text("-Payload: ${record.payload.toHexString()}")
                        }
                        nDef.ndefMessage?.records?.forEach { record ->
                            Text("Message Record #${record.id}")
                            Text("-TNF: ${record.tnf}")
                            Text("-Type: ${record.type.toHexString()}")
                            Text("-Payload: ${record.payload.toHexString()}")
                        }
                    }
                }

                "android.nfc.tech.NdefFormatable" -> {
                    // val nDef = NdefFormatable.get(tag)
                    Text("NdefFormatable")
                }

                "android.nfc.tech.MifareClassic" -> {
                    MifareClassic.get(tag)?.let { mc ->
                        Text("MifareClassic: ")
                        Text("-Type: " + when (mc.type) {
                            MifareClassic.TYPE_CLASSIC -> "TYPE_CLASSIC"
                            MifareClassic.TYPE_PLUS -> "TYPE_PLUS"
                            MifareClassic.TYPE_PRO -> "TYPE_PRO"
                            else -> "TYPE_UNKNOWN"
                        })
                        Text("-Size: " + when (mc.size) {
                            MifareClassic.SIZE_MINI -> "SIZE_MINI"
                            MifareClassic.SIZE_1K -> "SIZE_1K"
                            MifareClassic.SIZE_2K -> "SIZE_2K"
                            MifareClassic.SIZE_4K -> "SIZE_4K"
                            else -> "SIZE_UNKNOWN"
                        })
                        Text("-Block Count: ${mc.blockCount}")
                        Text("-Sector Count: ${mc.sectorCount}")
                    }
                }

                "android.nfc.tech.MifareUltralight" -> {
                    Text("MifareUltralight: ")
                }
            }
        }
    }
}