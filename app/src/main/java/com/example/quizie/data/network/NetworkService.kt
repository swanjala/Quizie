package com.example.quizie.data.network

import com.example.quizie.data.model.QuestionsDTO
import com.example.quizie.data.model.QuestionsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("/rest/kahoots/fb4054fc-6a71-463e-88cd-243876715bc1")
    suspend fun getQuestions(): Response<QuestionsResponseDTO>
}


