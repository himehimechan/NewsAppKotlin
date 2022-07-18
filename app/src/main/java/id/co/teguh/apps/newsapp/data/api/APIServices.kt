package id.co.teguh.apps.newsapp.data.api

import id.co.teguh.apps.newsapp.data.model.ArticlesModel
import retrofit2.Call
import retrofit2.http.*

interface APIServices {

    interface News {
        @GET("top-headlines")
        fun getNews(
            @Query("category") category: String,
            @Query("apiKey") apiKey: String,
        ): Call<BaseResult<List<ArticlesModel>>>

    }

}