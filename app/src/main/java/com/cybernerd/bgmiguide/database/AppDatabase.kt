package com.cybernerd.bgmiguide.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cybernerd.bgmiguide.database.gunInfo.GunInfoEntity
import com.cybernerd.bgmiguide.database.gunInfo.gunInfoDao

@Database(entities = [GunInfoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gunInfoDao(): gunInfoDao



    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).fallbackToDestructiveMigration().build()
    }
}