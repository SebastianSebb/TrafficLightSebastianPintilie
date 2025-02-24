import com.example.trafficlightsebastianpintilie.domain.TrafficLightColor
import com.example.trafficlightsebastianpintilie.domain.TrafficLightUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TrafficLightUseCaseTest {

    private lateinit var trafficLightUseCase: TrafficLightUseCase

    @Before
    fun setUp() {
        trafficLightUseCase = TrafficLightUseCase()
    }

    @Test
    fun `test initial traffic light state is GREEN`() = runTest {
        assertEquals(TrafficLightColor.GREEN, trafficLightUseCase.currentState.value)
    }

    @Test
    fun `test traffic light cycle transitions correctly`() = runTest {
        val job = launch {
            trafficLightUseCase.startTrafficLightCycle()
        }

        advanceTimeBy(4000)
        assertEquals(TrafficLightColor.GREEN, trafficLightUseCase.currentState.value)

        advanceTimeBy(1000)
        assertEquals(TrafficLightColor.YELLOW, trafficLightUseCase.currentState.value)

        advanceTimeBy(4000)
        assertEquals(TrafficLightColor.RED, trafficLightUseCase.currentState.value)

        job.cancel()
    }

    @Test
    fun `test traffic light continuous cycling`() = runTest {
        val job = launch {
            trafficLightUseCase.startTrafficLightCycle()
        }

        advanceTimeBy(4000)
        assertEquals(TrafficLightColor.GREEN, trafficLightUseCase.currentState.value)

        advanceTimeBy(1000)
        assertEquals(TrafficLightColor.YELLOW, trafficLightUseCase.currentState.value)

        advanceTimeBy(4000)
        assertEquals(TrafficLightColor.RED, trafficLightUseCase.currentState.value)

        advanceTimeBy(4000)
        assertEquals(TrafficLightColor.GREEN, trafficLightUseCase.currentState.value)

        job.cancel()
    }
}
