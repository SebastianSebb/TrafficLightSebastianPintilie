package com.example.trafficlightsebastianpintilie.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TrafficLightUseCase @Inject constructor() {
    private val _currentState = MutableStateFlow(TrafficLightColor.GREEN)
    val currentState: StateFlow<TrafficLightColor> = _currentState

    suspend fun startTrafficLightCycle() {
        while (true) {
            _currentState.value = TrafficLightColor.GREEN
            delay(LONG_DELAY)

            _currentState.value = TrafficLightColor.YELLOW
            delay(SHORT_DELAY)

            _currentState.value = TrafficLightColor.RED
            delay(LONG_DELAY)
        }
    }

    companion object DelayTimers {
        const val LONG_DELAY = 4000L
        const val SHORT_DELAY = 1000L
    }
}
