package com.knomster.ideas.domain.useCases

import com.knomster.ideas.domain.elements.IdeasMini

class SearchIdeasUseCase {
    fun search(ideas: List<IdeasMini>, searchText: String, links: List<Int>): List<IdeasMini> {
        return ideas
            .filter { it.name.contains(searchText) }
            .filter { it.links.containsAll(links) }
    }
}