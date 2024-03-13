package com.d121211017.submissiongithubuser.ui.detail.tablayout

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.d121211017.submissiongithubuser.ui.detail.fragments.FollowerFragment
import com.d121211017.submissiongithubuser.ui.detail.fragments.FollowingFragment

class SectionPageAdapter(
    activity : AppCompatActivity,
    ) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null

        when(position) {
            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }
}