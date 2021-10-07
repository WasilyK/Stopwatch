package com.gb.stopwatch.di

import com.gb.stopwatch.AboutActivity
import com.gb.stopwatch.model.*
import com.gb.stopwatch.viewmodel.StopwatchListOrchestrator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    factory<TimestampProvider> {
        object : TimestampProvider {
            override fun getMilliseconds() =
                System.currentTimeMillis()
        }
    }

    factory<TimestampMillisecondsFormatter> {
        TimestampMillisecondsFormatter()
    }

    factory<ElapsedTimeCalculator> {
        ElapsedTimeCalculator(timestampProvider = get())
    }

    factory<StopwatchStateCalculator> {
        StopwatchStateCalculator(
            timestampProvider = get(),
            elapsedTimeCalculator = get()
        )
    }

    factory<StopwatchStateHolder> {
        StopwatchStateHolder(
            stopwatchStateCalculator = get(),
            elapsedTimeCalculator = get(),
            timestampMillisecondsFormatter = get()
        )
    }

    viewModel<StopwatchListOrchestrator> {
        StopwatchListOrchestrator(
            stopwatchStateHolder = get(),
            stopwatchStateHolder2 = get()
        )
    }
}

val aboutActivityModel = module {

    scope<AboutActivity> {

        scoped<AboutTextGenerator> {
            AboutTextGenerator()
        }

    }
}