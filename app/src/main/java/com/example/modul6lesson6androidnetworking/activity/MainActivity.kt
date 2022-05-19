package com.example.modul6lesson6androidnetworking.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.modul6lesson6androidnetworking.databinding.ActivityMainBinding
import com.example.modul6lesson6androidnetworking.model.Post
import com.example.modul6lesson6androidnetworking.model.PostResponse
import com.example.modul6lesson6androidnetworking.network.retrofit.RetrofitHttp
import com.example.modul6lesson6androidnetworking.network.volley.VolleyHandler
import com.example.modul6lesson6androidnetworking.network.volley.VolleyHttp
import com.example.modul6lesson6androidnetworking.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        val post = Post(1, 1, "PDP", "Academy")

        /**
         * Volley
         */
//        apiVolleyList()
//        apiVolleyCreate(post)
//        apiVolleyUpdate(post)
//        apiVolleyDelete(post)

        /**
         * Retrofit
         */
//        apiRetrofitList()
//        apiRetrofitCreate(post)
//        apiRetrofitUpdate(post)
        apiRetrofitDelete(post)
    }

    private fun apiRetrofitDelete(post: Post) {
        RetrofitHttp.posterService.deletePost(post.id).enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                Logger.d(TAG, response.body().toString())
                binding.textView.text = response.body().toString()
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Logger.e(TAG, t.message.toString())
            }
        })
    }

    private fun apiRetrofitUpdate(post: Post) {
        RetrofitHttp.posterService.updatePost(post.id, post)
            .enqueue(object : Callback<PostResponse> {
                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {
                    Logger.d(TAG, response.body().toString())
                    binding.textView.text = response.body().toString()
                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Logger.e(TAG, t.message.toString())
                }
            })

    }

    private fun apiRetrofitCreate(post: Post) {
        RetrofitHttp.posterService.createPost(post).enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                Logger.d(TAG, response.body().toString())
                binding.textView.text = response.body().toString()
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Logger.e(TAG, t.message.toString())
            }
        })

    }

    private fun apiRetrofitList() {
        RetrofitHttp.posterService.listPost()
            .enqueue(object : Callback<ArrayList<PostResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<PostResponse>>,
                    response: Response<ArrayList<PostResponse>>
                ) {
                    Logger.d(TAG, response.body().toString())
                    binding.textView.text = response.body().toString()
                }

                override fun onFailure(
                    call: Call<ArrayList<PostResponse>>,
                    t: Throwable
                ) {
                    Logger.e(TAG, t.message.toString())
                }
            })

    }

//////////////////////////////////////////////////////////////////////////////

    private fun apiVolleyDelete(post: Post) {
        VolleyHttp.del(VolleyHttp.API_DELETE_POST + post.id, object : VolleyHandler {
            override fun onSuccess(response: String?) {
                binding.textView.text = response

            }

            override fun onError(error: String?) {

            }

        })

    }

    private fun apiVolleyUpdate(post: Post) {
        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + post.id, VolleyHttp.paramsUpdate(post),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    binding.textView.text = response
                }

                override fun onError(error: String?) {

                }
            })

    }

    private fun apiVolleyCreate(post: Post) {
        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(post), object :
            VolleyHandler {
            override fun onSuccess(response: String?) {
                binding.textView.text = response
            }

            override fun onError(error: String?) {

            }

        })

    }

    private fun apiVolleyList() {
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object : VolleyHandler {
            override fun onSuccess(response: String?) {

            }

            override fun onError(error: String?) {

            }
        })

    }

}