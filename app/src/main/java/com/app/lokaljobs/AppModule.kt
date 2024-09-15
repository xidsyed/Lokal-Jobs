package com.app.lokaljobs

import androidx.room.Room
import com.app.lokaljobs.Constants.BOOKMARK_DATABASE_NAME
import com.app.lokaljobs.data.BookmarkUseCases
import com.app.lokaljobs.data.GetJobsUseCase
import com.app.lokaljobs.data.local.JobDatabase
import com.app.lokaljobs.data.remote.JobAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {
    private val applicationContext  by lazy { LokalJobsApplication.context() }

    val jobApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://testapi.getlokalapp.com/common/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JobAPI::class.java)
    }

    val jobDatabase by lazy {
        Room.databaseBuilder(
            context = applicationContext, klass = JobDatabase::class.java, name = BOOKMARK_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    val JobDao by lazy { jobDatabase.jobDao }

    val GetJobsUseCase by lazy {
        GetJobsUseCase(jobApi)
    }

    val BookmarkJobUseCases by lazy {
        BookmarkUseCases(JobDao)
    }


}
