package com.shamilov.core.presentation.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shamilov.core.components.domain.model.BannerComponent
import com.shamilov.core.components.domain.model.BannersComponent
import com.shamilov.core.components.domain.model.HeaderComponent
import com.shamilov.core.presentation.components.*
import com.shamilov.core.presentation.main.viewmodel.MainState
import com.shamilov.core.presentation.main.viewmodel.MainViewModeFactory
import com.shamilov.core.presentation.main.viewmodel.MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(factory = MainViewModeFactory())
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {

        ToolbarComponent { navController.navigate("auth") }

        when (val value = state.value) {
            is MainState.Loading -> {
            }
            is MainState.Loaded -> {
                value.components.forEach { type ->
                    when (type) {
                        is HeaderComponent -> com.shamilov.core.presentation.components.HeaderComponent(
                            data = HeaderViewData(type.title, type.subtitle)
                        )
                        is BannerComponent -> com.shamilov.core.presentation.components.BannerComponent(
                            data = BannerViewData(
                                image = type.image,
                                bannerSize = BannerSize.valueOf(type.size.toUpperCase())
                            )
                        ) {}
                        is BannersComponent -> BannersComposable(
                            data = BannersViewData(
                                type.items.map {
                                    BannerViewData(
                                        it.image,
                                        BannerSize.valueOf(it.size.toUpperCase())
                                    )
                                }
                            ))
                    }
                }
            }
            is MainState.Error -> {
                Log.d("qwer", " state error -> ${value.message}")
            }
        }


//        BannerComponent(data = BannerViewData("", 100.dp)) { /* onClick() **/ }
//
//        DefaultSpacer()
//        HeaderComponent(data = HeaderViewData("Акции", "Скидка на заказ от 1000 ₽"))
//        DefaultSpacer(8.dp)
//        PromosComponent(
//            data = PromosViewData(
//                listOf(
//                    PromoViewData("", ""),
//                    PromoViewData("", ""),
//                    PromoViewData("", ""),
//                    PromoViewData("", ""),
//                    PromoViewData("", ""),
//                    PromoViewData("", ""),
//                    PromoViewData("", ""),
//                    PromoViewData("", ""),
//                )
//            )
//        ) {
//
//        }
//
//        DefaultSpacer()
//        HeaderComponent(data = HeaderViewData("Вы заказывали ранее", null))
//        DefaultSpacer(8.dp)
//
//        LazyRow {
//            items(10) {
//                ProductComponent(
//                    data = ProductViewData(
//                        "image",
//                        "Пицца Пепперони",
//                        "319 ₽",
//                        "Из дади пицца"
//                    ), modifier = Modifier.padding(horizontal = 8.dp)
//                ) {
//
//                }
//            }
//        }
//
//        DefaultSpacer()
//        HeaderComponent(data = HeaderViewData("Азиатская кухня", null))
//        DefaultSpacer(8.dp)
//        BannerComponent(data = BannerViewData("", 160.dp)) { /* onClick() **/ }
//        DefaultSpacer()
//        HeaderComponent(data = HeaderViewData("Горячие блюда", "Закажи от 2000 ₽ и получи по ебалу в подарок"))
//        DefaultSpacer(8.dp)
//        BannerComponent(data = BannerViewData("", 100.dp)) { /* onClick() **/ }
//        DefaultSpacer()
//        BannerComponent(data = BannerViewData("", 100.dp)) { /* onClick() **/ }
    }
}