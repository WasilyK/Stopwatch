package com.gb.stopwatch

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gb.stopwatch.model.AboutTextGenerator
import org.koin.android.ext.android.getKoin

class AboutActivity : AppCompatActivity() {

    private val scope = getKoin().createScope<AboutActivity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val textGenerator: AboutTextGenerator by scope.inject()

        findViewById<TextView>(R.id.about_tv).text = textGenerator.generateText()
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }
}