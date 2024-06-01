package ynsrc.example.android.examples.hw.camera

import android.Manifest
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraExample() {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    if (!cameraPermissionState.status.isGranted) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text("Request Permissions")
            }
        }
    } else {
        val cameraList = listOf(CameraSelector.DEFAULT_BACK_CAMERA, CameraSelector.DEFAULT_FRONT_CAMERA)
        var selectedCamera by remember { mutableStateOf(cameraList[0]) }

        Column {
            TabRow(cameraList.indexOf(selectedCamera)) {
                cameraList.forEach { camera ->
                    Tab(
                        selected = selectedCamera == camera,
                        onClick = { selectedCamera = camera },
                        text = {
                            Text(
                                when (camera) {
                                    CameraSelector.DEFAULT_BACK_CAMERA -> "Back"
                                    else -> "Front"
                                }
                            )
                        }
                    )
                }
            }

            CameraPreview(
                modifier = Modifier.fillMaxSize(),
                cameraSelector = selectedCamera
            )
        }
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
) {
    val scope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        modifier = modifier,
        factory = { context ->
            PreviewView(context).apply {
                this.scaleType = scaleType
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            }.also { previewView ->
                scope.launch {
                    val cameraProvider: ProcessCameraProvider = suspendCoroutine { continuation ->
                        ProcessCameraProvider.getInstance(context).also { future ->
                            future.addListener({
                                continuation.resume(future.get())
                            }, ContextCompat.getMainExecutor(context))
                        }
                    }

                    runCatching {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            Preview.Builder().build().also {
                                it.setSurfaceProvider(previewView.surfaceProvider)
                            }
                        )
                    }
                }
            }
        }
    )
}
