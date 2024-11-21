package com.knomster.ideas.presentation.viewModels.ideaScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knomster.ideas.domain.AppData
import com.knomster.ideas.domain.elements.IdeaBacklink
import com.knomster.ideas.domain.elements.IdeaData
import com.knomster.ideas.domain.elements.IdeaDataState
import com.knomster.ideas.domain.elements.IdeaElementAction
import com.knomster.ideas.domain.elements.IdeaElements
import com.knomster.ideas.domain.useCases.IdeaDataStateUseCase
import kotlinx.coroutines.launch

class IdeaScreenVM(ideaId: Int?, backlink: IdeaBacklink?) : ViewModel() {
    private val ideaDataStateUseCase = IdeaDataStateUseCase()

    private val _idea = MutableLiveData<IdeaData>()
    val idea: LiveData<IdeaData> = _idea

    private var ideaDataState: IdeaDataState

    init {
        val currentIdea = AppData.ideas.value?.find { it.id == ideaId } ?: newIdea(backlink)
        _idea.value = currentIdea
        ideaDataState = IdeaDataState(0, listOf(currentIdea))
    }

    fun edit(
        name: String
    ) {
        idea.value?.let {
            val newIdeaData = it.copy(name = name)
            _idea.postValue(newIdeaData)
            ideaDataState = ideaDataStateUseCase.addValue(ideaDataState, newIdeaData)
        }
    }

    fun edit(
        position: Int,
        action: IdeaElementAction,
        element: IdeaElements
    ) {
        idea.value?.let {
            val newIdeaData = it.copy(
                elements =
                when (action) {
                    IdeaElementAction.Add -> it.elements.toMutableList()
                        .apply { add(position, element) }

                    IdeaElementAction.Edit -> it.elements.toMutableList()
                        .apply { this[position] = element }

                    IdeaElementAction.Delete -> it.elements.toMutableList()
                        .apply { remove(element) }
                }
            )
            _idea.postValue(newIdeaData)
            ideaDataState = ideaDataStateUseCase.addValue(ideaDataState, newIdeaData)
        }
    }

    fun save() {
        idea.value?.let {
            viewModelScope.launch {
                AppData.insertIdea(it)
            }
        }
    }

    fun undo() {
        ideaDataState = ideaDataStateUseCase.undo(ideaDataState)
        _idea.postValue(getIdeaData())
    }

    fun redo() {
        _idea.postValue(getIdeaData())
        ideaDataState = ideaDataStateUseCase.redo(ideaDataState)
    }

    private fun getIdeaData() = ideaDataState.values[ideaDataState.position]

    private fun newIdea(backlink: IdeaBacklink?): IdeaData {
        var newId = 0
        val ids = AppData.ideas.value?.map { it.id } ?: emptyList()
        while (newId in ids) {
            newId++
        }
        return IdeaData(
            id = newId,
            name = "",
            editDate = System.currentTimeMillis(),
            elements = emptyList(),
            backLinks = if (backlink != null) listOf(backlink) else emptyList()
        )
    }
}