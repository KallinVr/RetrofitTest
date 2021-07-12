package com.example.retrofittest.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofittest.retrofit.model.Article
import com.example.retrofittest.retrofit.model.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException

class ViewModel constructor(private val repository: NewsRepository): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val newsList = MutableLiveData<List<Article>>()

    fun getAllNews(){
        val response = repository.getAllNews()
        response.enqueue(object : Callback<NewsModel>{
            override fun onResponse(
                call: Call<NewsModel>,
                response: Response<NewsModel>
            ) {
                if (response.isSuccessful) {
                    newsList.postValue(response.body()?.articles)
                }
            }
            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}

class ViewModelFactory constructor(private val repository: NewsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            ViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel is not founded")
        }
    }

}