package com.example.quizie.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizie.R

@Composable
fun ContinueButton(handleNextQuestionAction: () -> Unit) {
    Box(modifier = Modifier
        .width(150.dp)
        .height(50.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(Color(0xFFF2F2F2))
        .padding(horizontal = 10.dp)
        .clickable {
            handleNextQuestionAction.invoke()
        }
    ) {
        Text(
            modifier = Modifier.background(Color.Transparent)
                .align(Alignment.Center),
            text = stringResource(R.string.continue_button),
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 23.sp,
                fontWeight = FontWeight(700)
            ),
            textAlign = TextAlign.Center
        )
    }
}
