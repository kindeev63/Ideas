package com.knomster.ideas.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.knomster.ideas.domain.elements.IdeaData

@Dao
interface AppDao {

    @Query("SELECT * FROM table_ideas")
    fun getAllIdeas(): LiveData<List<IdeaData>>

    @Insert(IdeaData::class, onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertIdea(ideaData: IdeaData)

    @Query("DELETE FROM table_ideas WHERE id = :id")
    suspend fun deleteIdea(id: Int)
}