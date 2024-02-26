package com.example.quizie.fragments

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.quizie.ui.theme.QuizieTheme

fun Fragment.composeView(
    content: @Composable () -> Unit
) = ComposeView(
    requireContext()
).apply {
    setContent {
        QuizieTheme {
            content()
        }
    }
}