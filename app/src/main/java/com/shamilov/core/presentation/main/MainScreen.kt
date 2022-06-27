package com.shamilov.core.presentation.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shamilov.core.common.di.DaggerComponent
import com.shamilov.core.presentation.MainActivity
import com.shamilov.core.presentation.components.*
import com.shamilov.core.presentation.main.viewmodel.MainState
import com.shamilov.core.presentation.main.viewmodel.MainViewModeFactory
import com.shamilov.core.presentation.main.viewmodel.MainViewModel
import com.shamilov.core.presentation.utils.DefaultSpacer

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(factory = MainViewModeFactory(((LocalContext.current as MainActivity).application as DaggerComponent).appComponent))
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {

        ToolbarComposable { navController.navigate("auth") }

        when (val value = state.value) {
            is MainState.Loading -> {}
            is MainState.Loaded -> {
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
            is MainState.Error -> {
                Log.d("qwer", " state error -> ${value.message}")
            }
        }
    }
}