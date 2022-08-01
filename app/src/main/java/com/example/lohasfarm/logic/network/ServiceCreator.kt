package com.example.lohasfarm.logic.network

import com.example.lohasfarm.BASE_URL
import com.example.lohasfarm.TEST_BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class RetrofitBuild(
    url: String, client: OkHttpClient,
    gsonFactory: GsonConverterFactory
) {
    val retrofit: Retrofit = Retrofit.Builder().apply {
        baseUrl(url)
        client(client)
        addConverterFactory(gsonFactory)
    }.build()
}

object ServiceCreator {

    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 10L

    private fun create(isTestEnv: Boolean): Retrofit {
        val okHttpClientBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(LoggingInterceptor())
        }

        return RetrofitBuild(
            url = if (isTestEnv) {
                TEST_BASE_URL
            } else {
                BASE_URL
            },
            client = okHttpClientBuilder.build(),
            gsonFactory = GsonConverterFactory.create()
        ).retrofit
    }

    /**
     * 获取service
     */
    fun <T> create(service: Class<T>, isTestEnv: Boolean=false): T = create(isTestEnv).create(service)
}

internal class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        return chain.proceed(
            request
                .newBuilder()
                .addHeader("X-WX-SOURCE", "APP")
                .build()
        )
    }
}