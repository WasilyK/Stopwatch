package com.gb.stopwatch.model

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class StopwatchControl(
    private val scope: CoroutineScope,
    private var job: Job?,
    private val stopwatchStateHolder: StopwatchStateHolder,
    private val mutableTicker: MutableStateFlow<String>
) {

    fun start() {
        if (job == null) {
            job = startJob()
            stopwatchStateHolder.start()
        }
    }

    private fun startJob() =
        scope.launch {
            while (isActive) {
                mutableTicker.value = stopwatchStateHolder.getStringTimeRepresentation()
                delay(20)
            }
        }

    fun pause() {
        stopwatchStateHolder.pause()
        stopJob()
    }

    fun stop() {
        stopwatchStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        job?.cancel()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = "00:00:000"
    }
}