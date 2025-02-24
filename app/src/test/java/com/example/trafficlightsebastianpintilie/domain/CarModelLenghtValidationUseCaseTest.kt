package com.example.trafficlightsebastianpintilie.domain

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CarModelLengthValidationUseCaseTest {

    private lateinit var carModelLengthValidationUseCase: CarModelLenghtValidationUseCase

    @Before
    fun setUp() {
        carModelLengthValidationUseCase = CarModelLenghtValidationUseCase()
    }

    @Test
    fun `test car model length is valid when it is 3 characters or more`() {
        assertTrue(carModelLengthValidationUseCase.execute("Ford"))
        assertTrue(carModelLengthValidationUseCase.execute("Jeep"))
        assertTrue(carModelLengthValidationUseCase.execute("Audi"))
    }

    @Test
    fun `test car model length is invalid when it is less than 3 characters`() {
        assertFalse(carModelLengthValidationUseCase.execute("a"))
        assertFalse(carModelLengthValidationUseCase.execute("as"))
        assertFalse(carModelLengthValidationUseCase.execute(""))
    }
}
