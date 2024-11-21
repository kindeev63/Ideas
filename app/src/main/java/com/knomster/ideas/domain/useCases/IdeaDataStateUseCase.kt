package com.knomster.ideas.domain.useCases

import com.knomster.ideas.domain.elements.IdeaData
import com.knomster.ideas.domain.elements.IdeaDataState

class IdeaDataStateUseCase {
    fun undo(ideaDataState: IdeaDataState): IdeaDataState {
        return if (ideaDataState.position > 0) {
            ideaDataState.copy(position = ideaDataState.position - 1)
        } else ideaDataState
    }

    fun redo(ideaDataState: IdeaDataState): IdeaDataState {
        return if (ideaDataState.position < ideaDataState.values.size - 1) {
            ideaDataState.copy(position = ideaDataState.position + 1)
        } else ideaDataState
    }

    fun addValue(ideaDataState: IdeaDataState, value: IdeaData): IdeaDataState {
        return ideaDataState.copy(
            values = ideaDataState.values.subList(
                0,
                ideaDataState.position
            ) + listOf(value),
            position = ideaDataState.position + 1
        )
    }
}