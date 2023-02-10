package mx.ulai.test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.ulai.test.api.*
import mx.ulai.test.db.AppTestDB
import mx.ulai.test.model.ShoppAppResponse
import java.io.IOException

class FetchNextPageTask constructor(
    private val page: Int,
    private val apiLiverpool: ApiLiverpool,
    private val db: AppTestDB
): Runnable {
    private val _liveData = MutableLiveData<Resource<ShoppAppResponse>>()
    val liveData: LiveData<Resource<ShoppAppResponse>> = _liveData


    override fun run() {
        val current: ShoppAppResponse?  =
            db.shoppAppDao().selectFromRecordsPage(page)

        current?.let {
            _liveData.postValue(Resource.success(current))
            return
        }

        val newValue = try {
            val response  =
                apiLiverpool.getNextPage(page).execute()
            val apiResponse = ApiResponse.create(response)
            when(apiResponse){
                is SuccessResponse -> {
                    try{
                        db.beginTransaction()
                        db.shoppAppDao().insert(apiResponse.body)
                        db.setTransactionSuccessful()
                    } finally {
                        db.endTransaction()
                    }
                    Resource.success(apiResponse.body)
                }
                is EmptyResponse ->{
                    Resource.success(null)
                }
                is ErrorResponse -> {
                    Resource.error(null, apiResponse.errorMessage)
                }
            }
        } catch (e: IOException){
            Resource.error(null, e.message!!)
        }
        _liveData.postValue(newValue)
        return
    }
}