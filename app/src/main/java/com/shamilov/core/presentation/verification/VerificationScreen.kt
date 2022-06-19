package com.shamilov.core.presentation.verification

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shamilov.core.presentation.MainActivity
import com.shamilov.core.presentation.verification.viewmodel.CodeVerificationViewModel
import com.shamilov.core.presentation.verification.viewmodel.VerificationEffect
import com.shamilov.core.presentation.verification.viewmodel.VerificationMessage
import com.shamilov.core.presentation.verification.viewmodel.VerificationViewModelFactory
import com.shamilov.core.utils.BackButton
import com.shamilov.core.utils.DefaultSpacer
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CodeVerificationScreen(
    navController: NavController,
    viewModel: CodeVerificationViewModel = viewModel(factory = VerificationViewModelFactory((LocalContext.current as MainActivity).authUseCase))
) {
    val state = viewModel.state
    val message = viewModel::accept

    Box(modifier = Modifier.fillMaxSize()) {
        BackButton(onClick = { message(VerificationMessage.OnBackButtonClicked) })

        Column(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(32.dp)
        ) {
            OutlinedTextField(
                value = state.code,
                onValueChange = { message(VerificationMessage.ValidateCode(it)) },
                modifier = Modifier.fillMaxWidth(),
                shape = CircleShape,
                label = {
                    Text(text = "Enter your code")
                },
                enabled = state.codeFieldEnabled,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            DefaultSpacer()
            Button(
                onClick = { message(VerificationMessage.SendCode) },
                enabled = state.buttonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(text = "Send code")
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

    val context = LocalContext.current

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is VerificationEffect.OpenUserProfileScreen -> {
                    navController.navigate("main")
                }
                is VerificationEffect.NavigateBack -> navController.navigateUp()
                is VerificationEffect.ShowErrorMessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}