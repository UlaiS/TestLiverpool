package mx.ulai.test.repository

import androidx.lifecycle.LiveData
import mx.ulai.test.api.ApiLiverpool
import mx.ulai.test.api.ApiResponse
import mx.ulai.test.application.TestApplicationExecutors
import mx.ulai.test.db.AppTestDB
import mx.ulai.test.db.dao.ShoppAppDao
import mx.ulai.test.model.ShoppAppResponse
import mx.ulai.test.util.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordsRepository @Inject constructor(
    private val appExecutor: TestApplicationExecutors,
    private val shoppAppDao: ShoppAppDao,
    private val db: AppTestDB,
    private val apiLiverpool: ApiLiverpool
){
    private val recordsListRateLimiter = RateLimiter<Int>(2, TimeUnit.MINUTES)
    fun loadRecords(page: Int?): LiveData<Resource<ShoppAppResponse>> {
        return object: NetworkBoundResource<ShoppAppResponse, ShoppAppResponse>(appExecutor){
            override fun saveCallResult(item: ShoppAppResponse) {
                shoppAppDao.insert(item)
            }

            override fun shouldFetch(data: ShoppAppResponse?): Boolean =
                data == null || data.plpResults.records.isEmpty() || recordsListRateLimiter.shouldFetch(1)

            override fun loadFromDB(): LiveData<ShoppAppResponse> =   shoppAppDao.selectFromLiveData(page?: 1)


            override fun createCall(): LiveData<ApiResponse<ShoppAppResponse>> = apiLiverpool.getRecords()


            override fun onFetchFailed() {
                super.onFetchFailed()
                recordsListRateLimiter.reset(1)
            }
        }.asLiveData()
    }

    fun nextPage(page: Int): LiveData<Resource<ShoppAppResponse>> {
        val fetchNextPageTask = FetchNextPageTask(
            page = page,
            apiLiverpool = apiLiverpool,
            db = db
        )
        appExecutor.networkIO().execute(fetchNextPageTask)
        return fetchNextPageTask.liveData
    }
}