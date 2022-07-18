package id.co.teguh.apps.newsapp.ui.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import id.co.teguh.apps.newsapp.R
import id.co.teguh.apps.newsapp.data.model.ArticlesModel
import id.co.teguh.apps.newsapp.databinding.ActivityMainBinding
import id.co.teguh.apps.newsapp.ui.adapter.ListNewsAdapter
import id.co.teguh.apps.newsapp.ui.adapter.ListTopNewsAdapter
import id.co.teguh.apps.newsapp.ui.viewmodel.MainActivityModel
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity(), ListTopNewsAdapter.ListItemListener, ListNewsAdapter.ListItemListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityModel
    private lateinit var adapter: ListTopNewsAdapter
    private lateinit var adapterNews: ListNewsAdapter
    private var pDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainActivityModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        init()
        listener()
        setObserve()
    }

    private fun init(){
        adapter = ListTopNewsAdapter(this)
        binding.recyclerViewTop.adapter = adapter

        adapterNews = ListNewsAdapter(this)
        binding.recyclerView.adapter = adapterNews
    }

    private fun listener(){

    }

    private fun setObserve(){
        viewModel.newsDataForTopHeadlines.observe(
            this, {
                adapter.notifyDataSetChanged()
            }
        )
        viewModel.newsDataForDown.observe(
            this, {
                adapterNews.notifyDataSetChanged()
            }
        )
        viewModel.isLoading.observe(
            this, {
                if(it){
                    showProgressDialog()
                } else {
                    dismissProgressDialog()
                }
            }
        )
    }

    private fun showProgressDialog() {
        if (pDialog == null) {
            pDialog = Dialog(this)
            pDialog?.setContentView(R.layout.pop_up_loading)
            pDialog?.setCancelable(false)
        }
        pDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pDialog?.show()
        val window: Window? = pDialog?.window
        window?.setGravity(Gravity.CENTER)
    }

    private fun dismissProgressDialog() {
        if (pDialog != null && pDialog!!.isShowing) {
            pDialog?.dismiss()
        }
    }

    override fun onItemClick(position: Int) {
        Log.e("news", "klik")
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(viewModel.newsDataForTopHeadlines.value?.get(position)?.url)
        startActivity(i)
    }

    override fun getEntries(): List<ArticlesModel> {
        return viewModel.newsDataForTopHeadlines.value ?: ArrayList()
    }

    override fun onItemClickNews(position: Int) {
        Log.e("news", "klik")
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(viewModel.newsDataForDown.value?.get(position)?.url)
        startActivity(i)
    }

    override fun getEntriesNews(): List<ArticlesModel> {
        return viewModel.newsDataForDown.value ?: ArrayList()
    }

}