package com.example.trafficlightsebastianpintilie.presentation.inputscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.trafficlightsebastianpintilie.domain.CarModelLenghtValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val carModelLenghtValidationUseCase: CarModelLenghtValidationUseCase
) : ViewModel() {
    val inputText = mutableStateOf("")
    val isValidInput = mutableStateOf(false)

    fun onInputChanged(newInput: String) {
        inputText.value = newInput
        isValidInput.value = carModelLenghtValidationUseCase.execute(newInput)
    }

    fun submitInput(context: Context, carModel: String, navController: NavController) {
        if (isValidInput.value) {
            navController.navigate("traffic_light_screen/$carModel")
        } else {
            showToast(context)
        }
    }

    fun isButtonEnabled(): Boolean {
        return isValidInput.value
    }

    private fun showToast(context: Context) {
        Toast.makeText(
            context,
            "The model of the car needs to be at least 3 characters long.",
            Toast.LENGTH_SHORT
        ).show()
    }
}