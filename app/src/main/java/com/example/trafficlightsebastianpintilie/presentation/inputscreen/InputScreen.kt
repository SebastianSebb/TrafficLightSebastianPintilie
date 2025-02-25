package com.example.trafficlightsebastianpintilie.presentation.inputscreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.trafficlightsebastianpintilie.R

@Composable
fun InputScreen(
    context: Context,
    navController: NavController,
    viewModel: InputViewModel = hiltViewModel()
) {
    val carModel by remember { viewModel.inputText }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.input_screen_title),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        CarModelField(
            value = carModel,
            onValueChange = { viewModel.onInputChanged(it) },
            isLengthValid = viewModel.isLengthValid(),
            onDoneClick = { viewModel.submitInput(context, carModel, navController) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        NavigateToTrafficLight(
            onDoneClick = { viewModel.submitInput(context, carModel, navController) },
            isLengthValid = viewModel.isLengthValid()
        )
    }
}

@Composable
fun CarModelField(
    value: String,
    onValueChange: (String) -> Unit,
    isLengthValid: Boolean,
    onDoneClick: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            isError = !isLengthValid,
            label = { Text(stringResource(id = R.string.input_field_label)) },
            placeholder = { Text(stringResource(id = R.string.input_field_placeholder)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDoneClick()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
        )

        if (!isLengthValid) {
            Text(
                text = stringResource(id = R.string.input_screen_validation_error_text),
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun NavigateToTrafficLight(
    onDoneClick: () -> Unit,
    isLengthValid: Boolean
) {
    Button(
        onClick = {
            onDoneClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6200EE),
            contentColor = Color.White
        ),
        enabled = isLengthValid,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF6200EE))
    ) {
        Text(
            text = stringResource(id = R.string.lets_drive_button_text),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}
