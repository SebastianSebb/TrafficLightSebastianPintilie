package com.example.trafficlightsebastianpintilie.presentation.trafficlightscreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trafficlightsebastianpintilie.R
import com.example.trafficlightsebastianpintilie.domain.TrafficLightColor
import com.example.trafficlightsebastianpintilie.domain.TrafficLightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrafficLightViewModel @Inject constructor(
    private val trafficLightUseCase: TrafficLightUseCase
) : ViewModel() {
    val currentState = trafficLightUseCase.currentState

    fun startTrafficLightCycle() {
        viewModelScope.launch {
            trafficLightUseCase.startTrafficLightCycle()
        }
    }

    fun initiateDrive(context: Context) {
        when (currentState.value) {
            TrafficLightColor.RED -> {
                showToast(context, context.getString(R.string.driving_on_red))
            }

            TrafficLightColor.YELLOW -> {
                showToast(context, context.getString(R.string.driving_on_yellow))
            }

            TrafficLightColor.GREEN -> {
                showToast(context, context.getString(R.string.driving_on_green))
            }
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}