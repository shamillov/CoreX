package com.shamilov.core.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shamilov.core.R

@Composable
fun BackButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.ic_round_arrow_back_ios_24),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .padding(16.dp)
            .clickable { onClick() }
    )
}