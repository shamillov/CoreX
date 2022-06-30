package com.shamilov.core.profile.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shamilov.core.common.ui.composable.BackButton
import com.shamilov.core.common.ui.composable.LoadingButton
import com.shamilov.core.profile.presentation.viewmodel.ProfileEffect
import com.shamilov.core.profile.presentation.viewmodel.ProfileMessage
import com.shamilov.core.profile.presentation.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel,
) {
    val state = viewModel.state.value
    val message = viewModel::accept

    Box(modifier = Modifier.fillMaxSize()) {
        BackButton(onClick = { navController.popBackStack("main", false) })
        LoadingButton(
            isEnabled = state.isEnabled,
            isLoading = state.isLoading,
            buttonText = "Logout",
            onClick = { message(ProfileMessage.OnLogoutClicked) },
            modifier = Modifier
                .align(alignment = Alignment.Center)
        )
    }

    val context = LocalContext.current

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ProfileEffect.OpenAuthScreen -> navController.popBackStack("main", false)
                is ProfileEffect.ShowMessage -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}