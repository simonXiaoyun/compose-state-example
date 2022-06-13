package com.example.composestatedemo.learn

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun MyScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    // If the UI state contains an error, show snackbar
    if (true) {

        // `LaunchedEffect` will cancel and re-launch if
        // `scaffoldState.snackbarHostState` changes
        LaunchedEffect(scaffoldState.snackbarHostState) {
            // Show snackbar using a coroutine, when the coroutine is cancelled the
            // snackbar will automatically dismiss. This coroutine will cancel whenever
            // `state.hasError` is false, and only start when `state.hasError` is true
            // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Error message",
                actionLabel = "Retry message"
            )
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        /* ... */
    }
}