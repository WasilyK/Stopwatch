package com.gb.stopwatch

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gb.stopwatch.utils.viewById
import com.gb.stopwatch.viewmodel.StopwatchListOrchestrator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val stopwatchListOrchestrator: StopwatchListOrchestrator by viewModel()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewStopwatch()
        initViewStopwatch2()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when(item.itemId) {
            R.id.about_menu -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    private fun initViewStopwatch() {
        val textView by viewById<TextView>(R.id.text_time)
        scope.launch {
            stopwatchListOrchestrator.ticker.collect {
                textView.text = it
            }
        }

        val btnStart by viewById<Button>(R.id.button_start)
        val btnPause by viewById<Button>(R.id.button_pause)
        val btnStop by viewById<Button>(R.id.button_stop)

        btnStart.setOnClickListener {
            stopwatchListOrchestrator.control.start()
        }
        btnPause.setOnClickListener {
            stopwatchListOrchestrator.control.pause()
        }
        btnStop.setOnClickListener {
            stopwatchListOrchestrator.control.stop()
        }
    }

    private fun initViewStopwatch2() {
        val textView by viewById<TextView>(R.id.text_time_2)
        scope.launch {
            stopwatchListOrchestrator.ticker2.collect {
                textView.text = it
            }
        }

        val btnStart by viewById<Button>(R.id.button_start_2)
        val btnPause by viewById<Button>(R.id.button_pause_2)
        val btnStop by viewById<Button>(R.id.button_stop_2)

        btnStart.setOnClickListener {
            stopwatchListOrchestrator.control2.start()
        }
        btnPause.setOnClickListener {
            stopwatchListOrchestrator.control2.pause()
        }
        btnStop.setOnClickListener {
            stopwatchListOrchestrator.control2.stop()
        }
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}
