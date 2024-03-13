package com.d121211017.submissiongithubuser.ui.detail.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.d121211017.submissiongithubuser.databinding.FragmentFollowBinding
import com.d121211017.submissiongithubuser.model.FollowResponseItem
import com.d121211017.submissiongithubuser.ui.detail.recyclerview.RecyclerViewAdapter
import com.d121211017.submissiongithubuser.viewmodel.DetailViewModel


class FollowingFragment : Fragment() {

    private val detailViewModel by activityViewModels<DetailViewModel>()
    private lateinit var binding : FragmentFollowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(inflater)

        val layoutManager = LinearLayoutManager(context)
        binding.rvFollowing.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvFollowing.addItemDecoration(itemDecoration)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecyclerViewAdapter(arrayListOf())

        binding.rvFollowing.adapter = adapter

        detailViewModel.followingList.observe(viewLifecycleOwner){
            setFollowingRecyclerView(it)
        }

        detailViewModel.isLoadingUserFollowing.observe(viewLifecycleOwner){
            isLoading(it)
        }
    }

    private fun setFollowingRecyclerView(arrayList: List<FollowResponseItem?>?){
        val adapter = RecyclerViewAdapter(arrayList)
        binding.rvFollowing.adapter = adapter
    }

    private fun isLoading(isLoading: Boolean){
        if(isLoading){
            binding.followingPgBar.visibility = View.VISIBLE
        } else {
            binding.followingPgBar.visibility = View.INVISIBLE
        }
    }
}