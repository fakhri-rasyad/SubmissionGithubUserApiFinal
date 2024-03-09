package com.d121211017.submissiongithubuser.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.d121211017.submissiongithubuser.databinding.ActivityMainBinding
import com.d121211017.submissiongithubuser.model.ItemsItem
import com.d121211017.submissiongithubuser.ui.main.recyclerview.MainRecyclerViewAdapter
import com.d121211017.submissiongithubuser.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    mainScreenPlacehoder.visibility = View.INVISIBLE
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    mainViewModel.getUserList(searchView.text.toString())
                    Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)

        mainViewModel.isLoading.observe(this){
            isLoading(it)
        }

        mainViewModel.userList.observe(this){
            showMainRecycler(it)
        }

    }

    private fun showMainRecycler(userList : List<ItemsItem?>?){
        val adapter = MainRecyclerViewAdapter(userList)
        binding.rvUsers.adapter = adapter
    }

    private fun isLoading(isLoading: Boolean){
        if(isLoading){
            binding.mainProgressBar.visibility = View.VISIBLE
        } else {
            binding.mainProgressBar.visibility = View.INVISIBLE
        }
    }
}