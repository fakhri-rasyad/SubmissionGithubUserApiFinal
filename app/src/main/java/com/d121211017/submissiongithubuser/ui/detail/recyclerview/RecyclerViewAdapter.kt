package com.d121211017.submissiongithubuser.ui.detail.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.d121211017.submissiongithubuser.R
import com.d121211017.submissiongithubuser.model.FollowResponseItem

class RecyclerViewAdapter(private val followList: List<FollowResponseItem?>?) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val listItem = followList?.get(position)

        if (listItem != null) {
            Glide.with(holder.itemView.context)
                .load(listItem.avatarUrl)
                .circleCrop()
                .into(holder.imageView)
        }

        if (listItem != null) {
            holder.textView.text = listItem.login
        }
    }

    override fun getItemCount(): Int {
        return followList!!.size
    }

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val imageView : ImageView = item.findViewById(R.id.detail_rv_image)
        val textView : TextView = item.findViewById(R.id.detail_rv_text)
    }
}