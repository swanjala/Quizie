package com.example.quizie.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizie.composables.screens.CommonScreen
import com.example.quizie.questions.QuestionsViewModel
import dagger.android.support.AndroidSupportInjection
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
