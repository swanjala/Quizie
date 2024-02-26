package com.example.quizie.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChoicesDTO(
    val answer: String,
    val correct: Boolean
): DTOModel<Choices>() {
    override fun toValidResponse() = Choices(
        answer = answer,
        correct = correct
    )
}

