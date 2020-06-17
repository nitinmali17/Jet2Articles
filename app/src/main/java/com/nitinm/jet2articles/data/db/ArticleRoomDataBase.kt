package com.nitinm.jet2articles.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nitinm.jet2articles.data.model.ArticleDataEntity

@Database(entities = [ArticleDataEntity::class], version = 1, exportSchema = false)
abstract class ArticleRoomDataBase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleRoomDataBase? = null

        fun getDatabase(context: Context): ArticleRoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleRoomDataBase::class.java,
                    "articles_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}