package com.example.lohasfarm.logic.network

import com.example.lohasfarm.BASE_URL
import com.example.lohasfarm.TEST_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
            addInterceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .addHeader("X-WX-SOURCE", "app")
                        .build()
                )
            }
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
