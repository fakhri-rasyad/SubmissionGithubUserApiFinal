package com.d121211017.submissiongithubuser.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.d121211017.submissiongithubuser.R
import com.d121211017.submissiongithubuser.databinding.ActivityDetailBinding
import com.d121211017.submissiongithubuser.model.UserResponse
import com.d121211017.submissiongithubuser.ui.detail.tablayout.SectionPageAdapter
import com.d121211017.submissiongithubuser.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )

        const val username = ""
    }

    private lateinit var binding : ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SectionPageAdapter(this)
        val viewPager : ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        val tabs : TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        val username_get = intent.getStringExtra(username)

        detailViewModel.getUserData(username_get!!)

        detailViewModel.getFollowList(username_get)

        detailViewModel.isLoadingUserData.observe(this){
            isLoading(it)
        }

        detailViewModel.userData.observe(this){
            setUserData(it)
        }

        detailViewModel.isError.observe(this){
            showErrorToast(it)
        }

    }

    private fun isLoading(loading : Boolean){
        when(loading){
            true -> binding.userDataPgbar.visibility = View.VISIBLE
            false -> binding.userDataPgbar.visibility = View.INVISIBLE
        }
    }

    private fun setUserData(userdata : UserResponse?){
        if(userdata != null){
            Glide
                .with(this)
                .load(userdata.avatarUrl)
                .circleCrop()
                .into(binding.detailImage)
            binding.detailNameTxt.text = userdata.name
            binding.detailUsernameTxt.text = userdata.login
            binding.detailFollowerTxt.text = resources.getString(R.string.follower_count_d, userdata.followers)
            binding.detailFollowingTxt.text = resources.getString(R.string.following_count, userdata.following)
        }
    }

    private fun showErrorToast(isError : Boolean){
        if(isError) Toast.makeText(this, "Ups sepertinya ada yang salah", Toast.LENGTH_SHORT).show()
    }



}