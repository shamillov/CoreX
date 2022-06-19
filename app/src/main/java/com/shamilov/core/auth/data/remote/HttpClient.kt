package com.shamilov.core.auth.data.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {

    @Volatile
    private var retrofit: Retrofit? = null

    fun createHttpClient(): Retrofit {
        if (retrofit == null) {
            synchronized(this) {
                if (retrofit == null) {
                    val gson = GsonBuilder()
                        .setLenient()
                        .create()

                    retrofit = Retrofit.Builder()
                        .baseUrl("http://10.1.0.155:8035/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                }
            }
        }

        return retrofit!!
    }
}