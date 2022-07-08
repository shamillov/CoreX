package com.shamilov.core.components.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

sealed class Menu(open val title: String) {
    data class Category(override val title: String) : Menu(title)
    data class Products(override val title: String) : Menu(title)
}

fun generateContent(): List<Menu> = buildList {
    repeat(100) {
        if (it % 15 == 0) {
            add(Menu.Category("Header - $it"))
        } else {
            add(Menu.Products("Item - $it"))
        }
    }
}

@Composable
fun <T> SmartTabsList(
    content: List<T>,
    isTab: (T) -> Boolean,
    tab: @Composable (T, Boolean) -> Unit,
    item: @Composable (T) -> Unit,
) {
    val tabs = content.filter { isTab(it) }
    val indexes = content.mapTabs(isTab = isTab)
    val selectedTabIndex = remember { mutableStateOf(0) }
    val verticalListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(content, verticalListState) {
        snapshotFlow { verticalListState.firstVisibleItemIndex }
            .mapNotNull {
                val tabData = content.getOrNull(it)
                indexes[tabData]
            }
            .distinctUntilChanged()
            .collectLatest {
                selectedTabIndex.value = it
            }
    }

    val scrollToItem = scroller(
        verticalListState = verticalListState,
        coroutineScope = coroutineScope,
        smartTabsContent = content,
    )

    Column {
        SmartTabs(
            selectedTabIndex = selectedTabIndex.value,
            onTabSelected = { selectedTabIndex.value = it },
            scrollToItem = scrollToItem,
            tabs = tabs,
            smartTab = tab,
        )

        SmartTabsItems(
            listState = verticalListState,
            smartTabsContent = content,
            smartItem = item,
        )
    }
}

fun <T> List<T>.mapTabs(isTab: (T) -> Boolean): Map<T, Int> = buildMap {
    var headerIndex = -1 // Tabs are zero-based. -1 means tha no tab exist
    this@mapTabs.forEach {
        if (isTab(it)) {
            headerIndex++
        }
        this[it] = headerIndex
    }
}

@Composable
private fun <T> SmartTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    scrollToItem: (T) -> Unit,
    tabs: List<T>,
    smartTab: @Composable (T, Boolean) -> Unit,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onTabSelected(index)
                    scrollToItem(item)
                }
            ) {
                smartTab(item, selectedTabIndex == index)
            }
        }
    }
}

private fun <T> scroller(
    verticalListState: LazyListState,
    coroutineScope: CoroutineScope,
    smartTabsContent: List<T>,
): (T) -> Unit = {
    coroutineScope.launch {
        val tabIndex = smartTabsContent.indexOf(it)
        verticalListState.animateScrollToItem(index = tabIndex)
    }
}

@Composable
private fun <T> SmartTabsItems(
    listState: LazyListState,
    smartTabsContent: List<T>,
    smartItem: @Composable (T) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState
    ) {
        smartTabsContent.forEach {
            item {
                Box {
                    smartItem(it)
                }
            }
        }
    }
}