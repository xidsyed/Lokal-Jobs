package com.app.lokaljobs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JobEntity::class], version = 2)
abstract class JobDatabase : RoomDatabase() {
    abstract val jobDao: JobDao
}