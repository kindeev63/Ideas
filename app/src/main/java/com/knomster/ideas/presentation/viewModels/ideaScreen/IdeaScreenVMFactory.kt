package com.knomster.ideas.presentation.viewModels.ideaScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knomster.ideas.domain.elements.IdeaBacklink

class IdeaScreenVMFactory(private val ideaId: Int?, private val backlink: IdeaBacklink?): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return IdeaScreenVM(ideaId, backlink) as T
    }
}