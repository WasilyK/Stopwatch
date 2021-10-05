package com.gb.stopwatch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gb.stopwatch.model.StopwatchStateHolder

class OrchestratorFactory(
    private val stopwatchStateHolder: StopwatchStateHolder,
    private val stopwatchStateHolder_2: StopwatchStateHolder
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when(modelClass) {
            StopwatchListOrchestrator::class.java ->
                StopwatchListOrchestrator(stopwatchStateHolder, stopwatchStateHolder_2)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class")
        } as T
}