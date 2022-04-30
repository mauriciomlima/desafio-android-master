package com.picpay.desafio.android

import RequestPresenter
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.utils.mvp.RequestContract
import com.picpay.desafio.android.utils.CacheIntercept
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var presenter: RequestContract.Presenter
    lateinit var srlList: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var maadapter: UserListAdapter

    private val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        val fc = CacheIntercept()
        fc.create(this@MainActivity)
        OkHttpClient.Builder()
            .addInterceptor(fc)
            .cache(Cache(cacheDir, 10 * 1024 * 1024))
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        srlList = findViewById(R.id.srlList)
        srlList.setOnRefreshListener {
            requestUsers(service)
        }
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        maadapter = UserListAdapter()
        recyclerView.adapter = maadapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    var maResumed = false

    override fun onResume() {

        super.onResume()

        if (maResumed) {
            return
        } else {
            maResumed = true
            progressBar.visibility = View.VISIBLE
            presenter = RequestPresenter(this@MainActivity)
            requestUsers(service)
        }
    }

    private fun requestUsers(service : PicPayService){
        presenter.requestContactsList(service)
    }
}
