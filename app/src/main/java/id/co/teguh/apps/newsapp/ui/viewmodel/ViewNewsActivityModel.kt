package id.co.teguh.apps.newsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.teguh.apps.newsapp.data.model.ArticlesModel
import id.co.teguh.apps.newsapp.data.repository.NewsRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewNewsActivityModel: ViewModel() {
    var urlNews: String = ""

    init {

    }

}