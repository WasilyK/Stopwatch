package com.gb.stopwatch.viewmodel

import androidx.lifecycle.ViewModel
import com.gb.stopwatch.model.StopwatchControl
import com.gb.stopwatch.model.StopwatchStateHolder
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StopwatchListOrchestrator(
    stopwatchStateHolder: StopwatchStateHolder,
    stopwatchStateHolder_2: StopwatchStateHolder
) : ViewModel() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private var job: Job? = null
    private val mutableTicker = MutableStateFlow("")
    val ticker: StateFlow<String> = mutableTicker

    private var job_2: Job? = null
    private val mutableTicker_2 = MutableStateFlow("")
    val ticker_2: StateFlow<String> = mutableTicker_2

    val control = StopwatchControl(
        scope, job, stopwatchStateHolder, mutableTicker
    )

    val control_2 = StopwatchControl(
        scope, job_2, stopwatchStateHolder_2, mutableTicker_2
    )

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}
