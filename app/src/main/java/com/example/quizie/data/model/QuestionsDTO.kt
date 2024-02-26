package com.example.quizie.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionsDTO(
    val type: String,
    val question: String? = "",
    val time: Int? = 0,
    val points: Boolean = false,
    val pointsMultiplier: Int? = 0,
    val choices: List<ChoicesDTO> = emptyList(),
    val image: String
) : DTOModel<Questions>() {
    override fun toValidResponse() = Questions(
        type = type,
        question = question,
        time = time,
        points = points,
        pointsMultiplier = pointsMultiplier,
        choices = choices.map {
            it.getResponse() ?: Choices(
                "",
                false
            )
        },
        image = image
    )
}
