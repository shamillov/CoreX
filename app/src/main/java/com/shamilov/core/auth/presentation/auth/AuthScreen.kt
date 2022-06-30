package com.shamilov.core.auth.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shamilov.core.auth.presentation.auth.viewmodel.AuthEffect
import com.shamilov.core.auth.presentation.auth.viewmodel.AuthMessage
import com.shamilov.core.auth.presentation.auth.viewmodel.AuthViewModel
import com.shamilov.core.common.ui.composable.BackButton
import com.shamilov.core.common.ui.composable.DefaultSpacer
import com.shamilov.core.common.ui.composable.LoadingButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    val state = viewModel.state
    val message = viewModel::accept

    var phoneNumber by remember { mutableStateOf(state.phone) }
    val focusRequester = remember { FocusRequester() }

    Box(modifier = Modifier.fillMaxSize()) {
        BackButton(onClick = { message(AuthMessage.OnBackButtonClicked) })

        Column(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .focusRequester(focusRequester),
                shape = CircleShape,
                label = { Text(text = "Enter your phone number") },
                enabled = state.phoneFieldEnabled,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            DefaultSpacer()

            LoadingButton(
                isEnabled = state.buttonEnabled,
                isLoading = state.isLoading,
                buttonText = "Send message",
                onClick = { message(AuthMessage.SendPhone(phoneNumber)) },
            )

            DefaultSpacer()
            Text(
                text = "При входе или регистрации, вы принимаете условия пользовательского соглашения",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
            )
        }
    }

    val context = LocalContext.current

    LaunchedEffect(viewModel.effect) {
        focusRequester.requestFocus()

        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is AuthEffect.NavigateBack -> navController.navigateUp()
                is AuthEffect.ShowErrorMessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is AuthEffect.OpenVerificationScreen -> navController.navigate("verification")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
//    AuthScreen(rememberNavController())
}