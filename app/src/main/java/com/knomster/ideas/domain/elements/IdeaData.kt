package com.knomster.ideas.domain.elements

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_ideas")
data class IdeaData(
    @PrimaryKey
    val id: Int,
    val name: String,
    val editDate: Long,
    val elements: List<IdeaElements>,
    val backLinks: List<IdeaBacklink>
)
