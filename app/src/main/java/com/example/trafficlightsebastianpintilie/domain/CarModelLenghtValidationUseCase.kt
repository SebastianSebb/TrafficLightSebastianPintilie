package com.example.trafficlightsebastianpintilie.domain

import javax.inject.Inject

class CarModelLenghtValidationUseCase @Inject constructor() {
    fun execute(carModel: String): Boolean {
        return carModel.length >= 3
    }
}