import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trafficlightsebastianpintilie.domain.TrafficLightColor
import com.example.trafficlightsebastianpintilie.presentation.trafficlightscreen.TrafficLightViewModel

@Composable
fun TrafficLight(trafficLightViewModel: TrafficLightViewModel) {

    val currentColor = trafficLightViewModel.currentState.collectAsState().value

    val redColor = handleColorStates(currentColor, TrafficLightColor.RED)
    val yellowColor = handleColorStates(currentColor, TrafficLightColor.YELLOW)
    val greenColor = handleColorStates(currentColor, TrafficLightColor.GREEN)

    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TrafficLightCircle(color = redColor.value)
        TrafficLightCircle(color = yellowColor.value)
        TrafficLightCircle(color = greenColor.value)
    }

    LaunchedEffect(Unit) {
        trafficLightViewModel.startTrafficLightCycle()
    }
}

@Composable
fun TrafficLightCircle(color: Color) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
private fun handleColorStates(
    currentColor: TrafficLightColor,
    stateColor: TrafficLightColor
): State<Color> {
    return animateColorAsState(
        targetValue = if (currentColor == stateColor) currentColor.color else TrafficLightColor.GRAY.color,
        animationSpec = tween(durationMillis = 500)
    )
}
