package com.example.quizie.data.network

import com.example.quizie.data.model.QuestionsDTO
import retrofit2.http.GET
import retrofit2.http.Path

private const val CONTENT_UUID = "fb4054fc-6a71-463e-88cd-243876715bc1"
interface NetworkService {

    @GET("/rest/kahoots/$CONTENT_UUID")
    suspend fun getQuestions(
        @Path(CONTENT_UUID) contentUuid: String
    ): QuestionsDTO
}
