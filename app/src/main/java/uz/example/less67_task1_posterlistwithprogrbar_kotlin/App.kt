package uz.example.less67_task1_posterlistwithprogrbar_kotlin

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class App:Application() {
    companion object {
        val TAG = App::class.java.simpleName
        var instance: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val requestQueue: RequestQueue? = null
        get() {
            if (field == null) {
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    fun <T> addToRequestQueue(request: Request<T>) {
        request.tag = TAG
        requestQueue?.add(request)
    }
}