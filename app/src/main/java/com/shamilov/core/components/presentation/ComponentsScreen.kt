package com.shamilov.core.components.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.shamilov.core.common.ui.composable.DefaultSpacer
import com.shamilov.core.components.presentation.components.*
import com.shamilov.core.components.presentation.viewmodel.ComponentsEffect
import com.shamilov.core.components.presentation.viewmodel.ComponentsMessage
import com.shamilov.core.components.presentation.viewmodel.ComponentsState
import com.shamilov.core.components.presentation.viewmodel.ComponentsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: ComponentsViewModel,
) {
    val state = viewModel.state
    val message = viewModel::accept

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {

        ToolbarComposable { message(ComponentsMessage.OnProfileClicked) }

        when (val value = state.value) {
            is ComponentsState.Loading -> {}
            is ComponentsState.Loaded -> {
                value.components.forEach { type ->
                    when (type) {
                        is HeaderViewData -> HeaderComposable(type)
                        is BannerViewData -> BannerComposable(type) {}
                        is BannersViewData -> BannersComposable(type)
                        is CardsViewData -> CardsComposable(type) {}
                    }
                    DefaultSpacer()
                }
            }
            is ComponentsState.Error -> {
                Log.d("qwer", " state error -> ${value.message}")
            }
        }
    }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is ComponentsEffect.OpenUserProfileScreen -> navController.navigate("profile")
                is ComponentsEffect.OpenAuthScreen -> navController.navigate("auth")
            }
        }
    }
}