package com.shamilov.core.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shamilov.core.utils.BackButton
import com.shamilov.core.utils.DefaultSpacer

@Composable
fun AuthScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        BackButton(onClick = { /*TODO*/ })

        Column(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(32.dp)
        ) {
            TextField(
                value = "+7",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth()
            )
            DefaultSpacer()
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Send message")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}