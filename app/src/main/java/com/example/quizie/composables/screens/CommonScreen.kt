package com.example.quizie.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.quizie.R
import com.example.quizie.composables.components.ChoiceButton
import com.example.quizie.composables.components.ContinueButton
import com.example.quizie.composables.components.HeaderBanner
import com.example.quizie.composables.components.HeaderIconText
import com.example.quizie.composables.components.HeaderItemsDimens
import com.example.quizie.composables.components.HeaderText
import com.example.quizie.composables.components.QuestionText
import com.example.quizie.composables.model.ScreenTypeState.AnswerState
import com.example.quizie.composables.model.ScreenTypeState.CompletionState
import com.example.quizie.composables.model.ScreenTypeState.QuestionState
import com.example.quizie.questions.QuestionsViewModel

@Composable
fun CommonScreen(
    viewModel: QuestionsViewModel,
    handleContinueAction: () -> Unit,
) = with(viewModel) {
    val screenTypeState by screenTypeState.collectAsState()
    val uiContent by uiContent.collectAsState()
    val questionNumber by questionNumber.collectAsState()
    val questionCount by questionCount.collectAsState()

    Surface {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .paint(
                    painter = painterResource(id = R.mipmap.bg_image),
                    contentScale = ContentScale.FillBounds
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (screenTypeState) {
                is QuestionState -> {
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                    ) {
                        HeaderText(
                            fontSize = HeaderItemsDimens.fontSize,
                            text = "${questionNumber + 1}/${questionCount}"
                        )
                        Spacer(modifier = Modifier.width(70.dp))
                        HeaderIconText {
                            HeaderText(
                                fontSize = HeaderItemsDimens.fontSize2,
                                text = "Quiz"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }


                is AnswerState -> {
                    with(screenTypeState as AnswerState) {
                        correctnessText?.let {
                            HeaderBanner(
                                headerResources = if (correctAnswer) {
                                    Color(0xFF66BF39) to R.string.common_screen_banner_text_correct
                                } else {
                                    Color(0xFFFF3355) to R.string.common_screen_banner_text_wrong
                                }
                            )
                        }
                    }
                }

                is CompletionState -> {
                    //todo completions state with scores
                }
            }

            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .width(350.dp)
                    .height(250.dp),
                painter = rememberAsyncImagePainter(uiContent.image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            uiContent.question?.let { QuestionText(questionText = it) }

            Spacer(modifier = Modifier.height(20.dp))

            if (uiContent.choices.size == 2) {
                Spacer(modifier = Modifier.height(40.dp))
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                itemsIndexed(uiContent.choices) { index, item ->
                    ChoiceButton(
                        choiceText = item.answer,
                        uiState = screenTypeState,
                        answerDrawTracker = item.correct,
                        position = index,
                        answerStateTransition = {
                            answerStateTransition(
                                correctAnswer = item.correct,
                                correctText = item.answer,
                                userSelectedPosition = index,
                                score = if (uiContent.points) uiContent.pointsMultiplier ?: 0 else 0
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            ContinueButton(handleContinueAction)
        }
    }
}
