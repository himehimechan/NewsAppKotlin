package id.co.teguh.apps.newsapp.utils

import android.app.Application
import android.content.Context
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.imdb.GanderIMDB


class MyContext : Application() {
    override fun onCreate() {
        instance = this
        // For In Memory DB (Data retained in memory lost on app close)
        Gander.setGanderStorage(GanderIMDB.getInstance())
        super.onCreate()
    }

    companion object {
        private var instance: MyContext? = null

        // or return instance.getApplicationContext();
        val context: Context?
            get() = instance
        // or return instance.getApplicationContext();

        external fun getEKeys() : String
    }


}