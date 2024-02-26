package com.example.quizie.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizie.composables.model.ScreenTypeState
import com.example.quizie.composables.model.ScreenTypeState.AnswerState
import com.example.quizie.composables.model.ScreenTypeState.CompletionState
import com.example.quizie.composables.model.ScreenTypeState.QuestionState
import com.example.quizie.data.datastore.DataRepository
import com.example.quizie.data.model.Questions
import com.example.quizie.data.model.initialSetter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionsViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    // Screen type and ui content
    private var _uiScreenTypeState =
        MutableStateFlow<ScreenTypeState>(QuestionState(isTimedOut = false))
    val screenTypeState: StateFlow<ScreenTypeState> = _uiScreenTypeState.asStateFlow()

    private var _uiContent = MutableStateFlow(initialSetter())
    val uiContent: StateFlow<Questions> = _uiContent.asStateFlow()

    // Question activity trackers
    private var _questionNumber = MutableStateFlow(0)
    val questionNumber: StateFlow<Int> = _questionNumber.asStateFlow()

    private var _questionCount = MutableStateFlow(0)
    val questionCount: StateFlow<Int> = _questionCount.asStateFlow()

    private var _accumulatedScore = MutableStateFlow<Int>(1)
    val accumulatedScore: StateFlow<Int> = _accumulatedScore.asStateFlow()

    private lateinit var questions: List<Questions>

    init {
        viewModelScope.launch {
            fetchRemoteQuestions()
        }
    }

    private suspend fun fetchRemoteQuestions() {
        questions = dataRepository.fetchRemoteQuestions()
        displayQuestions()
    }

    private fun displayQuestions() {
        if (questions.isNotEmpty()) {
            _questionCount.value = questions.size
            _uiContent.value = questions[questionNumber.value]
        }
    }

    fun answerStateTransition(
        correctAnswer: Boolean,
        correctText: String,
        userSelectedPosition: Int,
        score: Int // to be used in completion state, not implemented
    ) {
        if (correctAnswer) {
            _accumulatedScore.value = accumulatedScore.value + 1
        }
        _uiScreenTypeState.value = AnswerState(
            correctAnswer,
            correctText,
            userSelectedPosition
        )
    }

    private fun setCompletionState(score: Int) {
        _uiScreenTypeState.value = CompletionState(score)
    }

    fun loadNextQuestion() {
        if (questions.lastIndex == questionNumber.value) {
            return // to implement a completion state.
        } else {
            _questionNumber.value = questionNumber.value + 1
            _uiScreenTypeState.value = QuestionState()
            _uiContent.value = questions[questionNumber.value]
        }
    }
}
