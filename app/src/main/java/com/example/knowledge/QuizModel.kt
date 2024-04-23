package com.example.knowledge

data class QuizModel(
    val id : String = "",
    val title : String = "",
    val subtitle : String = "",
    val time : String = "",
    val questionList : List<QuestionModel>
)

data class QuestionModel(
    val question : String = "",
    val options : List<String> = emptyList(),
    val correct : String = "",
)
