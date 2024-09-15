package com.app.lokaljobs.data.local.service

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.lokaljobs.data.local.model.JobEntity

@Database(entities = [JobEntity::class], version = 2)
abstract class JobDatabase : RoomDatabase() {
    abstract val jobDao: JobDao
}