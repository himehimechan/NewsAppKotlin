package id.co.teguh.apps.newsapp.data.api

import com.ashokvarma.gander.GanderInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import id.co.teguh.apps.newsapp.BuildConfig
import id.co.teguh.apps.newsapp.utils.MyContext


class APICaller {
    private var gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()

    companion object {
        val shared = APICaller()
    }


    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addConverterFactory(GsonConverterFactory.create()).build()

    private fun getClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(GanderInterceptor(MyContext.context!!).showNotification(true))
            .connectTimeout(60.toLong(), TimeUnit.SECONDS)
            .readTimeout(60.toLong(), TimeUnit.SECONDS)
            .writeTimeout(60.toLong(), TimeUnit.SECONDS)
            .build()
    }

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

}