package id.co.teguh.apps.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.co.teguh.apps.newsapp.R
import id.co.teguh.apps.newsapp.data.model.ArticlesModel
import id.co.teguh.apps.newsapp.databinding.ListItemBinding
import id.co.teguh.apps.newsapp.databinding.ListItemForTopHeadlinesBinding
import id.co.teguh.apps.newsapp.utils.DateFormat

class ListNewsAdapter(private val listener: ListItemListener) :
        RecyclerView.Adapter<ListNewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(listener.getEntriesNews().size > position) {
            val item = listener.getEntriesNews()[position]
            holder.populate(item, position)
        }
    }

    override fun getItemCount(): Int = listener.getEntriesNews().size

    interface ListItemListener {
        fun onItemClickNews(position: Int)
        fun getEntriesNews(): List<ArticlesModel>
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)

        fun populate(item: ArticlesModel, position: Int) {

            binding.newsTitle.text = item.title
            binding.newsPublicationTime.text =  DateFormat.shared.formatDateTimeNormal(item.publishedAt)
            if (item.urlToImage.isNullOrEmpty()) {
                Picasso.get()
                    .load(R.drawable.samplenews)
                    .fit()
                    .centerCrop()
                    .into(binding.img)
            } else {
                Picasso.get()
                    .load(item.urlToImage)
                    .fit()
                    .centerCrop()
                    .error(R.drawable.samplenews)
                    .into(binding.img)
            }

            binding.newsImage.setOnClickListener {
                listener.onItemClickNews(position)
            }

        }

        init {

        }
    }
}