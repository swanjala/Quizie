package com.example.quizie.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizie.R
import com.example.quizie.composables.model.ScreenTypeState
import com.example.quizie.composables.model.ScreenTypeState.AnswerState
import com.example.quizie.composables.model.ScreenTypeState.QuestionState
import com.example.quizie.composables.model.ScreenTypeState.CompletionState

import com.example.quizie.ui.theme.QuizieTheme

@Composable
fun ChoiceButton(
    position: Int = 0,
    uiState: ScreenTypeState = QuestionState(),
    answerDrawTracker: Boolean = false,
    choiceText: String,
    answerStateTransition: () -> Unit
) {
    val (color, icon) = choiceButtonResourceSelector(position, answerDrawTracker, uiState)
    Box(
        modifier = Modifier
            .width(185.dp)
            .height(116.dp)
            .clickable {
                if (uiState is QuestionState) {
                    answerStateTransition.invoke()
                } else {
                    //do nothing
                }
            }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .align(Alignment.Center)
                .background(color = color)
                .width(185.dp)
                .height(105.dp)
        ) {
            if (uiState is QuestionState) {
                Image(
                    modifier = Modifier
                        .padding(start = 5.dp, top = 5.dp)
                        .width(25.dp)
                        .height(25.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "",
                )
            } else {
                Spacer(Modifier.height(38.dp))
            }

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .width(200.dp),
                text = choiceText,
                style = MaterialTheme.typography.labelSmall.copy(
                    shadow = Shadow(
                        color = Color.DarkGray, offset = Offset(0.0f, 3.5f), blurRadius = 1f
                    )
                ),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(700)
            )
        }

        if (uiState is AnswerState) {
            Image(
                modifier = Modifier
                    .align(
                        if (position.answerTopIconPredicate()) Alignment.TopStart else Alignment.TopEnd
                    )
                    .size(23.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = icon),
                contentDescription = "",
            )
        }
    }
}

private fun choiceButtonResourceSelector(
    position: Int,
    correctAnswerDraw: Boolean,
    uiState: ScreenTypeState
): Pair<Color, Int> {

    return when (uiState) {
        is AnswerState -> {
            val userSelectionIndex = uiState.selectedAnswerPosition.coerceIn(0..3)

            when (position) {
                userSelectionIndex -> {
                    if (uiState.correctAnswer) {
                        Color(0xFF66BF39) to R.drawable.icon_correct
                    } else {
                        Color(0xFFFF3355) to R.mipmap.wrong
                    }
                }

                else -> {
                    if (correctAnswerDraw) {
                        Color(0xFF66BF39) to R.drawable.icon_correct
                    } else {
                        Color(0xFFFF99AA) to R.mipmap.wrong
                    }
                }

            }
        }

        is QuestionState -> {
            when (position) {
                0 -> Color(0xFFE21B3C) to R.drawable.icon_triangle
                1 -> Color(0xFF1368CE) to R.drawable.icon_diamond
                2 -> Color(0xFFD89E00) to R.drawable.icon_circle
                3 -> Color(0xFF26890C) to R.drawable.icon_square
                else -> {
                    // In this unlikely event then the buttons will have a disabled look
                    Color.Gray to R.drawable.icon_square
                }
            }
        }

        is CompletionState -> TODO()
    }
}

@Preview
@Composable
fun ChoiceButtonPreview() {
    QuizieTheme {
        ChoiceButton(
            uiState = QuestionState(),
            choiceText = "London",
            answerStateTransition = {}
        )
    }

}