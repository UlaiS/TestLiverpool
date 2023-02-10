package mx.ulai.test.ui.adapters.records

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import mx.ulai.test.R
import mx.ulai.test.application.TestApplicationExecutors
import mx.ulai.test.databinding.RecordItemBinding
import mx.ulai.test.model.Records
import mx.ulai.test.ui.adapters.MainAdapter

class RecordsAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: TestApplicationExecutors,
    private val repoClickCallback: ((Records)->Unit)?
) : MainAdapter<Records, RecordItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Records>(){
        override fun areItemsTheSame(oldItem: Records, newItem: Records): Boolean {
            return oldItem.productId == newItem.productId && oldItem.skuRepositoryId == newItem.skuRepositoryId
        }

        override fun areContentsTheSame(oldItem: Records, newItem: Records): Boolean {
            return oldItem.category == newItem.category && oldItem.productType == newItem.productType
        }

    }
) {
    override fun createBinding(parent: ViewGroup): RecordItemBinding {
        val binding = DataBindingUtil.inflate<RecordItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.record_item,
            parent,
            false,
            dataBindingComponent
        )
        binding.root.setOnClickListener{
            binding.record?.let {
                repoClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: RecordItemBinding, item: Records) {
        binding.record = item
    }
}