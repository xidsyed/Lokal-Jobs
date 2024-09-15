package com.app.lokaljobs.di

import androidx.room.Room
import com.app.lokaljobs.Constants.BOOKMARK_DATABASE_NAME
import com.app.lokaljobs.LokalJobsApplication
import com.app.lokaljobs.data.local.service.JobDatabase
import com.app.lokaljobs.data.remote.service.JobService
import com.app.lokaljobs.data.repository.JobsRepositoryImpl
import com.app.lokaljobs.domain.JobsRepository
import com.app.lokaljobs.domain.JobsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JobsModule {
    private val applicationContext by lazy { LokalJobsApplication.context() }

    private val JobService by lazy {
        Retrofit.Builder()
            .baseUrl("https://testapi.getlokalapp.com/common/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JobService::class.java)
    }

    private val jobDatabase by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = JobDatabase::class.java,
            name = BOOKMARK_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    val JobDao by lazy { jobDatabase.jobDao }

    val JobsRepository: JobsRepository by lazy { JobsRepositoryImpl(JobService, jobDao = JobDao) }

    val JobsUseCase: JobsUseCase by lazy { JobsUseCase(JobsRepository) }

}
