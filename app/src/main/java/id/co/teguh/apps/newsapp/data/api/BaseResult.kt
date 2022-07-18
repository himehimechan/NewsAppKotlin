package id.co.teguh.apps.newsapp.data.api

data class BaseResult<T>(
    val status: String = "",
    val articles: T?,
    val totalResults: Int

)