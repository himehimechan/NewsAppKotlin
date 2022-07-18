package id.co.teguh.apps.newsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.teguh.apps.newsapp.data.model.ArticlesModel
import id.co.teguh.apps.newsapp.data.repository.NewsRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityModel: ViewModel() {

    var newsDataForTopHeadlines = MutableLiveData<List<ArticlesModel>>()
    var newsDataForDown = MutableLiveData<List<ArticlesModel>>()
    var getCountTopHeadlines = 5
    var isLoading = MutableLiveData<Boolean>()

    init {
        getData()
    }

    private fun getData(){
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            NewsRequest().getNewsGeneral(
                "technology"
            ) {
                isLoading.postValue(false)
                Log.d("getData", it.articles.toString())
                if (it.status == "ok" && it.articles != null) {
                    Log.d("getData", "sukses")
                    newsDataForTopHeadlines.postValue(it.articles.subList(0, getCountTopHeadlines-1))
                    newsDataForDown.postValue(it.articles.subList(getCountTopHeadlines, it.articles.size-1))
                } else {
                    Log.d("getData", "gagal")
                }
            }
        }
    }

}