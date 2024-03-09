package com.d121211017.submissiongithubuser.ui.main.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.d121211017.submissiongithubuser.R
import com.d121211017.submissiongithubuser.model.ItemsItem

class MainRecyclerViewAdapter(private val userArrayList : List<ItemsItem?>?) : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewAdapter.ViewHolder, position: Int) {
        val users = userArrayList?.get(position)

        Glide.with(holder.itemView.context)
            .load(users?.avatarUrl)
            .circleCrop()
            .centerInside()
            .into(holder.imageView)

        holder.textView.text = users?.login
    }

    override fun getItemCount(): Int {
        return userArrayList?.size ?: 0
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val imageView : ImageView = item.findViewById(R.id.main_rv_image)
        val textView : TextView = item.findViewById(R.id.main_rv_text)
    }
}