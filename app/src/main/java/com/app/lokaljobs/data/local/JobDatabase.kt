package com.app.lokaljobs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Job::class], version = 1)
abstract class JobDatabase : RoomDatabase() {
    abstract val jobDao: JobDao
}