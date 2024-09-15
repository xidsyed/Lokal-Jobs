package com.app.lokaljobs.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(job: Job)

    @Delete
    suspend fun delete(job: Job)

    @Query("SELECT * FROM Job")
    fun selectJobs(): Flow<List<Job>>

    @Query("SELECT * FROM Job where id = :id")
    suspend fun selectJob(id: Int): Job?

}