package com.example.trafficlightsebastianpintilie.domain

import javax.inject.Inject

private const val CAR_MODEL_MINIMUM_LENGTH = 3

class CarModelLenghtValidationUseCase @Inject constructor() {
    fun execute(carModel: String): Boolean {
        return carModel.length >= CAR_MODEL_MINIMUM_LENGTH
    }
}