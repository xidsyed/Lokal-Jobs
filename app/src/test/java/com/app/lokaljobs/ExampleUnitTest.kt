package com.app.lokaljobs

import com.app.lokaljobs.data.remote.JobAPI
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

    private val jobApi = Retrofit.Builder()
        .baseUrl("https://testapi.getlokalapp.com/common/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JobAPI::class.java)

    private val jobsDto = runBlocking{ jobApi.getResponse(1) }

    @Test
    fun testApi() {
        assert(jobsDto!=null)
    }

    @Test
    fun testJobModel() {
        val list = jobsDto!!.toJobs()
        println(list.joinToString("\n\n"))
    }

}