package mx.ulai.test.api

import androidx.lifecycle.LiveData
import mx.ulai.test.model.ShoppAppResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiLiverpool {

    @GET("plp")
    fun getRecords(): LiveData<ApiResponse<ShoppAppResponse>>

    @GET("plp")
    fun getNextPage(
        @Query("page") page: Int
    ): Call<ShoppAppResponse>

}