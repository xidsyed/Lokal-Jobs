package com.app.lokaljobs.di

import androidx.room.Room
import com.app.lokaljobs.Constants.BOOKMARK_DATABASE_NAME
import com.app.lokaljobs.LokalJobsApplication
import com.app.lokaljobs.domain.BookmarkUseCases
import com.app.lokaljobs.domain.GetJobsUseCase
import com.app.lokaljobs.data.local.JobDatabase
import com.app.lokaljobs.data.remote.JobService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JobServiceModule {
    private val applicationContext  by lazy { LokalJobsApplication.context() }

    val jobService by lazy {
        Retrofit.Builder()
            .baseUrl("https://testapi.getlokalapp.com/common/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JobService::class.java)
    }

    val jobDatabase by lazy {
        Room.databaseBuilder(
            context = applicationContext, klass = JobDatabase::class.java, name = BOOKMARK_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    val JobDao by lazy { jobDatabase.jobDao }

    val GetJobsUseCase by lazy {
        GetJobsUseCase(jobService)
    }

    val BookmarkJobUseCases by lazy {
        BookmarkUseCases(JobDao)
    }


}
