package com.app.lokaljobs

import com.app.lokaljobs.data.remote.JobService
import com.app.lokaljobs.data.local.toJobs
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val jobService = Retrofit.Builder()
        .baseUrl("https://testapi.getlokalapp.com/common/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JobService::class.java)

    private val jobResponse = runBlocking{ jobService.getResponse(1) }

    @Test
    fun testApi() {
        assert(jobResponse!=null)
    }

    @Test
    fun testJobModel() {
        val list = jobResponse!!.toJobs()
        println(list.joinToString("\n\n"))
    }

}