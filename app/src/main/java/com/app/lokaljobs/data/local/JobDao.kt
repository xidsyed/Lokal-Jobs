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
    suspend fun upsert(job: JobEntity)

    @Delete
    suspend fun delete(job: JobEntity)

    @Query("SELECT * FROM JobEntity")
    fun selectJobs(): Flow<List<JobEntity>>

    @Query("SELECT * FROM JobEntity where id = :id")
    suspend fun selectJob(id: Int): JobEntity?

}