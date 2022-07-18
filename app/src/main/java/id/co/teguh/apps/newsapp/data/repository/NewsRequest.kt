package id.co.teguh.apps.newsapp.data.repository

import id.co.teguh.apps.newsapp.BuildConfig
import id.co.teguh.apps.newsapp.data.api.APICaller
import id.co.teguh.apps.newsapp.data.api.APIServices
import id.co.teguh.apps.newsapp.data.api.BaseResult
import id.co.teguh.apps.newsapp.data.model.ArticlesModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRequest {

    fun getNewsGeneral(category: String,
                   callback: (BaseResult<List<ArticlesModel>>) -> Unit) {
        val request = APICaller.shared.buildService(APIServices.News::class.java)
        val caller = request.getNews(category, BuildConfig.API_KEY)
        caller.enqueue(object: Callback<BaseResult<List<ArticlesModel>>> {
            override fun onFailure(call: Call<BaseResult<List<ArticlesModel>>>, t: Throwable) {
                callback(BaseResult("", null, 0))
            }

            override fun onResponse(call: Call<BaseResult<List<ArticlesModel>>>, response: Response<BaseResult<List<ArticlesModel>>>) {
                response.body()?.let {
                    return callback(it)
                }
                response.errorBody()?.string()?.let {
                    return callback(BaseResult("", null, 0))
                }
                callback(BaseResult("", null, 0))
            }

        })
    }

}