package com.knomster.ideas.domain.elements

data class IdeasMini(
    val id: Int,
    val name: String,
    val text: String,
    val links: List<Int>
)
