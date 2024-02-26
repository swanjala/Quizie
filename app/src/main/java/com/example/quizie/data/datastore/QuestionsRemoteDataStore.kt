package com.example.quizie.data.datastore

import com.example.quizie.data.model.Questions
import com.example.quizie.data.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface QuestionsRemoteDataStore {
    suspend fun fetchQuestions(): List<Questions>
}

class QuestionsRemoteDataStoreImpl @Inject constructor(
    private val networkService: NetworkService
) : QuestionsRemoteDataStore {
    override suspend fun fetchQuestions(): List<Questions> {

        val questions = mutableListOf<Questions>()
        val response = networkService.getQuestions().body()

        flowOf(
            response?.questions
        ).flowOn(Dispatchers.IO)
            .collect { questionsDTO ->
                questionsDTO?.map {
                    it.getResponse()?.let { question ->
                        questions.add(
                            question
                        )
                    }
                }
            }

        return questions
    }
}
