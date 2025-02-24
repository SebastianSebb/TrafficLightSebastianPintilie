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
            delay(4000)

            _currentState.value = TrafficLightColor.YELLOW
            delay(1000)

            _currentState.value = TrafficLightColor.RED
            delay(4000)
        }
    }
}