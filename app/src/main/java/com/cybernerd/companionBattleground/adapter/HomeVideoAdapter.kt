package com.cybernerd.companionBattleground.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.model.Videos
import com.cybernerd.companionBattleground.utils.ClickListener
import kotlinx.android.synthetic.main.item_home_video_layout.view.*

class HomeVideoAdapter(private val context: Context, val clickListeners: ClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<Videos> = arrayListOf()

    fun setHomeVideo(list: List<Videos>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_video_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        Glide.with(this.context)
            .load(list[position].thumbnail)
            .placeholder(R.drawable.battleground_logo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.youtube_thumbnail)

        holder.itemView.youtube_title.text = list[position].title

        holder.itemView.setOnClickListener {
            clickListeners.homeVideoClickListener(list[position])
        }

    }


     class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}