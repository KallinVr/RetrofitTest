package com.example.retrofittest.retrofit

class NewsRepository(private val retrofitService: Service) {
    fun getAllNews() = retrofitService.getAllNews()
}