package com.gb.stopwatch.viewmodel

import androidx.lifecycle.ViewModel
import com.gb.stopwatch.model.StopwatchControl
import com.gb.stopwatch.model.StopwatchStateHolder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StopwatchListOrchestrator(
    stopwatchStateHolder: StopwatchStateHolder,
    stopwatchStateHolder2: StopwatchStateHolder
) : ViewModel() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private var job: Job? = null
    private val mutableTicker = MutableStateFlow("")
    val ticker: StateFlow<String> = mutableTicker

    private var job2: Job? = null
    private val mutableTicker2 = MutableStateFlow("")
    val ticker2: StateFlow<String> = mutableTicker2

    val control = StopwatchControl(
        scope, job, stopwatchStateHolder, mutableTicker
    )

    val control2 = StopwatchControl(
        scope, job2, stopwatchStateHolder2, mutableTicker2
    )

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}
