package com.example.quizie.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quizie.composables.screens.CommonScreen
import com.example.quizie.questions.QuestionsViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: QuestionsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[QuestionsViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = composeView {
        CommonScreen(
            viewModel = viewModel,
            handleContinueAction = ::handleContinueAction
        )
    }

    private fun handleContinueAction() {
        viewModel.loadNextQuestion()
    }
}