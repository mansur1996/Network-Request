package com.example.modul6lesson6androidnetworking.model

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MyApplication : Application() {

    companion object{
        var instance : MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    //Volley settings
    private val requestQueue: RequestQueue? = null
        get() {
            if (field == null) {
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue?.add(request)
    }
}

