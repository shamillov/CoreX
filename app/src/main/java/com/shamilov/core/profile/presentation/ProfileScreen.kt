package com.shamilov.core.profile.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.shamilov.core.profile.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { }, modifier = Modifier.align(alignment = Alignment.Center)) {
            Text(text = "Logout")
        }
    }
}