package com.example.retrofittest.retrofit

import com.example.retrofittest.retrofit.model.NewsModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Service {

    @GET("/v2/top-headlines?country=ru&apiKey=eace38bdebbe4441904bd779697a2425")
    fun getAllNews(): Call<NewsModel>

    companion object{
        private var retrofitService: Service? = null

        fun getInstance(): Service {
            if (retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(Service::class.java)
            }
            return retrofitService!!
        }
    }

}