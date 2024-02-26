package com.example.quizie.data.datastore

import com.example.quizie.data.model.Questions
import javax.inject.Inject

interface DataRepository {
    suspend fun fetchRemoteQuestions(): List<Questions>
}

class DataRepositoryImpl @Inject constructor(
    private val questionsRemoteDataStore: QuestionsRemoteDataStore,
) : DataRepository {

    override suspend fun fetchRemoteQuestions(): List<Questions> {
        return questionsRemoteDataStore.fetchQuestions()
    }

}

