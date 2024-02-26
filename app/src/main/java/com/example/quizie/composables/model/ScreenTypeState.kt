package com.example.quizie.composables.model

sealed class ScreenTypeState {
    data class QuestionState(
        val isTimedOut: Boolean = false
    ) : ScreenTypeState()
    data class AnswerState(
        val correctAnswer: Boolean = false,
        val correctnessText: String? = "Wrong",
        val selectedAnswerPosition: Int = 0
    ) : ScreenTypeState()
    data class CompletionState(val score: Int): ScreenTypeState() // todo implement a completion state
}
