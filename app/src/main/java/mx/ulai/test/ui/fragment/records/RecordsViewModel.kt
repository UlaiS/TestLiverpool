package mx.ulai.test.ui.fragment.records

import androidx.lifecycle.*
import mx.ulai.test.model.Records
import mx.ulai.test.model.ShoppAppResponse
import mx.ulai.test.repository.RecordsRepository
import mx.ulai.test.repository.Resource
import mx.ulai.test.repository.Status
import javax.inject.Inject

class RecordsViewModel @Inject constructor(repository: RecordsRepository) : ViewModel() {
    private val page = MutableLiveData(0)

    private val nextPageHandler = NextPageHandler(repository)

    val loadMoreStatus: LiveData<LoadMoreState>
        get() = nextPageHandler.loadMoreState

    var record: MutableLiveData<Resource<ShoppAppResponse>> = Transformations
        .switchMap(page) {
            repository.loadRecords(it)
        } as MutableLiveData<Resource<ShoppAppResponse>>


    fun retry() {
        record.value?.data?.page?.let {
            record.value?.data?.page = it
        }
    }

    fun loadNextPage(){
        record.value?.data?.totalPage?.let { totalPages ->
            if (record.value?.data?.page?.plus(1)!!  > totalPages)
                return
            nextPageHandler.queryNextPage(record.value?.data?.page?.plus(1)!!)
        }
    }

    class LoadMoreState(val isRunning: Boolean, val errorMessage: String?){
        private var handleError = false

        val errorMessageIfNotHandled: String?
            get() {
                if(handleError){
                    return null
                }
                handleError = true
                return errorMessage
            }
    }

    inner class NextPageHandler(private val repository: RecordsRepository):
        Observer<Resource<ShoppAppResponse>> {

        private var nextPageLiveData: LiveData<Resource<ShoppAppResponse>>? = null
        val loadMoreState = MutableLiveData<LoadMoreState>()

        init {
            reset()
        }

        fun queryNextPage(currentPage: Int){
            unregister()
            nextPageLiveData = repository.nextPage(currentPage)
            loadMoreState.value = LoadMoreState(
                isRunning = true,
                errorMessage = null
            )
            nextPageLiveData?.observeForever(this)
        }

        override fun onChanged(result: Resource<ShoppAppResponse>?) {
            if(result == null){
                reset()
            }else{
                when(result.status){
                    Status.FINALIZADO ->{
                        unregister()
                        loadMoreState.value = LoadMoreState(
                            isRunning = false,
                            errorMessage = null
                        )
                        val list: MutableList<Records> = mutableListOf()
                        record.value?.data?.plpResults?.records?.let { list.addAll(it) }
                        result.data?.plpResults?.records?.let { list.addAll(it) }

                        result.data?.plpResults?.records = list
                        record.value = result
                    }
                    Status.ERROR -> {
                        unregister()
                        loadMoreState.value = LoadMoreState(
                            isRunning = false,
                            errorMessage = result.message
                        )

                    }
                    Status.CARGANDO ->{

                    }
                }
            }
        }

        private fun unregister(){
            nextPageLiveData?.removeObserver(this)
            nextPageLiveData = null
        }

        fun reset(){
            unregister()
            loadMoreState.value = LoadMoreState(
                isRunning = false,
                errorMessage = null
            )
        }
    }
}