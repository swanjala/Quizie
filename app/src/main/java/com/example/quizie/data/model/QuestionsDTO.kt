package com.example.quizie.data.model

import com.example.quizie.data.model.ChoicesDTO

data class QuestionsDTO(
    val type: String,
    val question: String,
    val time: Int,
    val points: Boolean,
    val pointsMultiplier: Int,
    val choices: List<ChoicesDTO>
)