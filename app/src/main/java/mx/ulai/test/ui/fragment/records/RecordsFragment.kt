package mx.ulai.test.ui.fragment.records

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.ulai.test.R
import mx.ulai.test.databinding.FragmentRecordsBinding
import mx.ulai.test.ui.adapters.records.RecordsAdapter
import mx.ulai.test.ui.common.RetryCallback
import mx.ulai.test.ui.fragment.MainFragment
import mx.ulai.test.util.autoCleared

class RecordsFragment: MainFragment<FragmentRecordsBinding, RecordsViewModel>() {


    private var adapter by autoCleared<RecordsAdapter>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.retryCallback = object : RetryCallback {
            override fun retry() {
                viewModel.retry()
            }
        }

        View.inflate(context, R.layout.header, dataBinding.content)

        val adapter = RecordsAdapter(dataBindingComponent, appExecutors){
        }

        binding.recordList.layoutManager = LinearLayoutManager(view.context)
        binding.recordList.adapter = adapter
        this.adapter = adapter
        observe()

    }

    private fun observe() {
        viewModel.record.observe(viewLifecycleOwner, Observer { record ->
            adapter.submitList(record.data?.plpResults?.records)

        })

        viewModel.loadMoreStatus.observe(viewLifecycleOwner, Observer { loading ->
            if(loading == null){
                binding.loading = false
            } else {
                binding.loading = loading.isRunning
                val error = loading.errorMessageIfNotHandled
                if(error != null){
                    Log.d("TAG1", "Error")
                }
            }
        })

        binding.recordList.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1){
                    viewModel.loadNextPage()
                }
            }
        })

    }

    override val layoutRes: Int
        get() = R.layout.fragment_records

    override fun getViewModel(): Class<RecordsViewModel> {
        return RecordsViewModel::class.java
    }
}