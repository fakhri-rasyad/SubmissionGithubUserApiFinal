package com.d121211017.submissiongithubuser.ui.detail.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.d121211017.submissiongithubuser.databinding.FragmentFollowerBinding
import com.d121211017.submissiongithubuser.model.FollowResponseItem
import com.d121211017.submissiongithubuser.ui.detail.recyclerview.RecyclerViewAdapter
import com.d121211017.submissiongithubuser.viewmodel.DetailViewModel


class FollowerFragment : Fragment() {

    private val detailViewModel by activityViewModels<DetailViewModel>()
    private lateinit var binding : FragmentFollowerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerBinding.inflate(layoutInflater)

        val layoutManager = LinearLayoutManager(context)
        binding.rvFollower.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvFollower.addItemDecoration(itemDecoration)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewAdapter = RecyclerViewAdapter(arrayListOf())

        binding.rvFollower.adapter = recyclerViewAdapter

        detailViewModel.followerList.observe(viewLifecycleOwner){
            setFollowerRecyclerView(it)
        }

        detailViewModel.isLoadingUserFollower.observe(viewLifecycleOwner){
            isLoading(it)
        }
    }

    private fun setFollowerRecyclerView(arrayList: List<FollowResponseItem?>?){
        val adapter = RecyclerViewAdapter(arrayList)
        binding.rvFollower.adapter = adapter
    }

    private fun isLoading(isLoading: Boolean){
        if(isLoading){
            binding.followerPgBar.visibility = View.VISIBLE
        } else {
            binding.followerPgBar.visibility = View.INVISIBLE
        }
    }

}