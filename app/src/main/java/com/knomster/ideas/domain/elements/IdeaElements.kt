package com.knomster.ideas.domain.elements

sealed class IdeaElements {
    data class IdeaTextElement(val text: String): IdeaElements()
    data class IdeaImageElement(val id: Int): IdeaElements()
    data class IdeaFileElement(val id: Int): IdeaElements()
    data class IdeaLinksElement(val ids: List<Int>): IdeaElements()
}