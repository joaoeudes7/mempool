package com.jedev.mempool.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jedev.mempool.ui.features.listRankingNodes.ListRankingLightning
import com.jedev.mempool.ui.theme.MemPoolTheme
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm = koinViewModel<MainViewModel>()

            val scope = rememberCoroutineScope()
            val snackBarHostState = remember { SnackbarHostState() }

            LaunchedEffect(true) {
                vm.snackBarMessage.collect { message ->
                    scope.launch {
                        snackBarHostState.showSnackbar(message)
                    }
                }
            }

            MemPoolTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            title = {
                                Text("MemPool | Lightning ⚡")
                            }
                        )
                    },
                    content = {
                        ListRankingLightning(Modifier.padding(it))
                    },
                    snackbarHost = {
                        SnackbarHost(hostState = snackBarHostState)
                    }
                )
            }
        }
    }
}
