package id.co.teguh.apps.newsapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import id.co.teguh.apps.newsapp.R
import id.co.teguh.apps.newsapp.databinding.ActivityMainBinding
import id.co.teguh.apps.newsapp.databinding.ActivityViewNewsBinding
import id.co.teguh.apps.newsapp.ui.viewmodel.MainActivityModel
import id.co.teguh.apps.newsapp.ui.viewmodel.ViewNewsActivityModel

class ViewNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewNewsBinding
    private lateinit var viewModel: ViewNewsActivityModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewNewsActivityModel::class.java]
        binding = ActivityViewNewsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        init()
        listener()
        setObserve()
    }

    private fun init(){

        viewModel.urlNews = intent.getStringExtra("url").toString()
        binding.webView.visibility = View.GONE
        binding.webView.isScrollbarFadingEnabled = true
        binding.webView.isHorizontalScrollBarEnabled = true
//        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.userAgentString = "AndroidWebView"
        //Clear the cache
        binding.webView.clearCache(true)
        //Make the webview load the specified URL
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
                binding.webView.visibility = View.VISIBLE
            }
        }
        binding.webView.loadUrl(viewModel.urlNews)
    }

    private fun listener(){

    }

    private fun setObserve(){

    }
}