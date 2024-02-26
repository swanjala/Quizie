package com.example.quizie.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.fromHtml

@Composable
fun QuestionText(questionText: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .align(Alignment.CenterStart),
            text = fromHtml(questionText, FROM_HTML_MODE_COMPACT).toAnnotatedString(),
            textAlign = TextAlign.Center
        )
    }
}
