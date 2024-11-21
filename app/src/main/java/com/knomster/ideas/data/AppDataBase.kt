package com.knomster.ideas.data

import android.content.Context
import androidx.room.Room
import com.knomster.ideas.domain.elements.IdeaData

@androidx.room.Database(entities = [IdeaData::class], version = 2)
abstract class AppDataBase : androidx.room.RoomDatabase() {
    abstract fun getDao(): AppDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDataBase::class.java,
                    name = "ideas.db"
                )
                    .build()
            }
        }
    }
}