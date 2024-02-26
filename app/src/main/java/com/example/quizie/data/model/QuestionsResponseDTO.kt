package com.example.quizie.data.model

data class QuestionsResponseDTO(
    val uuid: String,
    val questions : List<QuestionsDTO>
)
