package com.gb.stopwatch

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gb.stopwatch.model.*
import com.gb.stopwatch.viewmodel.OrchestratorFactory
import com.gb.stopwatch.viewmodel.StopwatchListOrchestrator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var stopwatchListOrchestrator: StopwatchListOrchestrator
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val orchestratorFactory = OrchestratorFactory(
        StopwatchStateHolderFactory.create(),
        StopwatchStateHolderFactory.create()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatchListOrchestrator = ViewModelProvider(this, orchestratorFactory)
            .get(StopwatchListOrchestrator::class.java)

        initViewStopwatch()
        initViewStopwatch_2()
    }

    private fun initViewStopwatch() {
        val textView = findViewById<TextView>(R.id.text_time)
        scope.launch {
            stopwatchListOrchestrator.ticker.collect {
                textView.text = it
            }
        }

        findViewById<Button>(R.id.button_start).setOnClickListener {
            stopwatchListOrchestrator.control.start()
        }
        findViewById<Button>(R.id.button_pause).setOnClickListener {
            stopwatchListOrchestrator.control.pause()
        }
        findViewById<Button>(R.id.button_stop).setOnClickListener {
            stopwatchListOrchestrator.control.stop()
        }
    }

    private fun initViewStopwatch_2() {
        val textView_2 = findViewById<TextView>(R.id.text_time_2)
        scope.launch {
            stopwatchListOrchestrator.ticker_2.collect {
                textView_2.text = it
            }
        }

        findViewById<Button>(R.id.button_start_2).setOnClickListener {
            stopwatchListOrchestrator.control_2.start()
        }
        findViewById<Button>(R.id.button_pause_2).setOnClickListener {
            stopwatchListOrchestrator.control_2.pause()
        }
        findViewById<Button>(R.id.button_stop_2).setOnClickListener {
            stopwatchListOrchestrator.control_2.stop()
        }
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}
