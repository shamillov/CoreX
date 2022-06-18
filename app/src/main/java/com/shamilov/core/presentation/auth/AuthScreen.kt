package com.shamilov.core.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shamilov.core.presentation.auth.viewmodel.AuthEffect
import com.shamilov.core.presentation.auth.viewmodel.AuthMessage
import com.shamilov.core.presentation.auth.viewmodel.AuthViewModel
import com.shamilov.core.utils.BackButton
import com.shamilov.core.utils.DefaultSpacer
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel(),
) {
    val state = viewModel.state
    val message = viewModel::accept

    var phoneNumber by remember { mutableStateOf(state.phone) }

    Box(modifier = Modifier.fillMaxSize()) {
        BackButton(onClick = { message(AuthMessage.OnBackButtonClicked) })

        Column(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(32.dp)
        ) {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                modifier = Modifier.fillMaxWidth(),
                shape = CircleShape,
                label = {
                    Text(text = "Enter your phone number")
                },
                enabled = state.phoneFieldEnabled,
                maxLines = 1,
            )
            DefaultSpacer()
            Button(
                onClick = { message(AuthMessage.SendPhone("")) },
                enabled = state.buttonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(text = "Send message")
                }
            }

            DefaultSpacer()
            Text(
                text = "При входе или регистрации, вы принимаете условия пользовательского соглашения",
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        }
    }
    
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is AuthEffect.NavigateBack -> navController.popBackStack()
                is AuthEffect.ShowErrorMessage -> {
                    // TODO: show error message
                }
                is AuthEffect.OpenVerificationScreen ->  {
                    // TODO: open verification screen
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen(rememberNavController())
}