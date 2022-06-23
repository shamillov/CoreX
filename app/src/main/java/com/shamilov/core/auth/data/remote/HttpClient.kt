package com.shamilov.core.auth.data.remote

import com.google.gson.GsonBuilder
import com.shamilov.core.components.data.ComponentDeserializer
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {

    @Volatile
    private var retrofit: Retrofit? = null

    fun createHttpClient(token: String?): Retrofit {
        if (retrofit == null) {
            synchronized(this) {
                if (retrofit == null) {
                    val headerInterceptor = Interceptor { chain ->
                        val requestBuilder = chain.request()
                            .newBuilder()
                            .addHeader("Authorization", "Bearer $token")

                        chain.proceed(requestBuilder.build())
                    }

                    val okHttpClient = OkHttpClient.Builder()
                        .addInterceptor(headerInterceptor)
                        .build()

                    val gson = GsonBuilder()
                        .setLenient()
                        .registerTypeAdapter(ComponentDeserializer::class.java, ComponentDeserializer())
                        .create()

                    retrofit = Retrofit.Builder()
                        .baseUrl("http://192.168.1.230:8035/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(okHttpClient)
                        .build()
                }
            }
        }

        return retrofit!!
    }
}