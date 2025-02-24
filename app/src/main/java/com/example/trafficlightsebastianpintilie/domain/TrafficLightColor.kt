package com.example.trafficlightsebastianpintilie.domain

import androidx.compose.ui.graphics.Color

data class TrafficLightColor(
    val color: Color,
    val name: String
) {
    companion object {
        val RED = TrafficLightColor(Color.Red, "RED")
        val YELLOW = TrafficLightColor(Color.Yellow, "YELLOW")
        val GREEN = TrafficLightColor(Color.Green, "GREEN")
        val GRAY = TrafficLightColor(Color.Gray, "GRAY")
    }
}
