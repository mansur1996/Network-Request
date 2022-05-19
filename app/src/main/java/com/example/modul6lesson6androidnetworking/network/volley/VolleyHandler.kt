package com.example.modul6lesson6androidnetworking.network.volley

interface VolleyHandler {
    fun onSuccess(response : String?)
    fun onError(error : String?)
}