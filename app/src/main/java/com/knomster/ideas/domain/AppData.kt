package com.knomster.ideas.domain

import androidx.lifecycle.LiveData
import com.knomster.ideas.data.AppDao
import com.knomster.ideas.domain.elements.IdeaData

object AppData {
    lateinit var ideas: LiveData<List<IdeaData>>
    private lateinit var appDao: AppDao

    fun setAppDao(appDao: AppDao) {
        this.appDao = appDao
        ideas = appDao.getAllIdeas()
    }

    suspend fun insertIdea(ideaData: IdeaData) {
        appDao.insertIdea(ideaData)
    }

    suspend fun deleteIdea(id: Int) {
        appDao.deleteIdea(id)
    }
}