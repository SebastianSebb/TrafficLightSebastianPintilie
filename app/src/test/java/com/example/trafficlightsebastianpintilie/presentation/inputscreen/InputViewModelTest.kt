import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.trafficlightsebastianpintilie.domain.CarModelLenghtValidationUseCase
import com.example.trafficlightsebastianpintilie.presentation.inputscreen.InputViewModel
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class InputViewModelTest {

    private lateinit var viewModel: InputViewModel
    private lateinit var carModelLenghtValidationUseCase: CarModelLenghtValidationUseCase
    private lateinit var context: Context
    private lateinit var navController: NavController

    @Before
    fun setUp() {
        carModelLenghtValidationUseCase = mockk()
        context = mockk()
        navController = mockk()

        viewModel = InputViewModel(carModelLenghtValidationUseCase)
    }

    @Test
    fun `test onInputChanged updates the inputText and isValidInput when input length is valid`() =
        runTest {
            every { carModelLenghtValidationUseCase.execute("Ford") } returns true

            viewModel.onInputChanged("Ford")

            assertEquals("Ford", viewModel.inputText.value)
            assertEquals(true, viewModel.isValidInput.value)
        }

    @Test
    fun `test onInputChanged updates the inputText and isValidInput when input length is invalid`() =
        runTest {
            every { carModelLenghtValidationUseCase.execute("a") } returns false

            viewModel.onInputChanged("a")

            assertEquals("a", viewModel.inputText.value)
            assertEquals(false, viewModel.isValidInput.value)
        }

    @Test
    fun `test submitInput shows toast when input is invalid`() = runTest {
        every { carModelLenghtValidationUseCase.execute("a") } returns false

        mockkStatic(Toast::class)

        val toastMock = mockk<Toast>(relaxed = true)

        every { Toast.makeText(any(), any<String>(), any()) } returns toastMock

        viewModel.onInputChanged("a")
        viewModel.submitInput(context, "a", navController)

        verify { toastMock.show() }
    }

    @Test
    fun `test submitInput calls navigate when input is valid`() = runTest {
        every { carModelLenghtValidationUseCase.execute("Ford") } returns true

        val navController = mockk<NavController>(relaxed = true)

        viewModel.onInputChanged("Ford")
        viewModel.submitInput(context, "Ford", navController)

        verify { navController.navigate("traffic_light_fragment/Ford") }
    }
}
