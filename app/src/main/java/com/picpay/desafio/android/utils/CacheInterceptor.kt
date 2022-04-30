package com.picpay.desafio.android.utils

import android.content.Context
import com.android.generalextensionlibrary.util.NetworkUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import java.io.IOException

class CacheIntercept() : Interceptor {

    lateinit var ctx : Context

    fun create(context: Context) {
        ctx = context
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (!NetworkUtil.isNetworkAvailable(ctx)) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }
        return chain.proceed(builder.build())
    }
}