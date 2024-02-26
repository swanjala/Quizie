package com.example.quizie.data.model

import java.io.Serializable

data class Questions(
    val type: String,
    val question: String?,
    val time: Int?,
    val points: Boolean,
    val pointsMultiplier: Int?,
    val choices: List<Choices>,
    val image: String,
) : Serializable
fun initialSetter() = Questions(
    type = "",
    question = "",
    time = 0,
    points = false,
    pointsMultiplier = 0,
    choices = emptyList(),
    image = ""
)
