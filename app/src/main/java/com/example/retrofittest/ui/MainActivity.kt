package com.example.retrofittest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.retrofit.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    private val retrofitService = Service.getInstance()
    private val adapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory(NewsRepository(retrofitService)))
            .get(ViewModel::class.java)

        binding.mainRecyclerView.adapter = adapter

        viewModel.newsList.observe(this, Observer{
            adapter.setNewsList(it)
        })

        viewModel.getAllNews()
    }
}