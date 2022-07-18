package id.co.teguh.apps.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.co.teguh.apps.newsapp.R
import id.co.teguh.apps.newsapp.data.model.ArticlesModel
import id.co.teguh.apps.newsapp.databinding.ListItemForTopHeadlinesBinding

class ListTopNewsAdapter(private val listener: ListItemListener) :
        RecyclerView.Adapter<ListTopNewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemForTopHeadlinesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(listener.getEntries().size > position) {
            val item = listener.getEntries()[position]
            holder.populate(item, position)
        }
    }

    override fun getItemCount(): Int = listener.getEntries().size

    interface ListItemListener {
        fun onItemClick(position: Int)
        fun getEntries(): List<ArticlesModel>
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemForTopHeadlinesBinding.bind(view)

        fun populate(item: ArticlesModel, position: Int) {

            binding.headline.text = item.title
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

            binding.card1.setOnClickListener {
                listener.onItemClick(position)
            }

        }

        init {

        }
    }
}