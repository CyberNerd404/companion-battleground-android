package com.cybernerd404.bgmiguide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.model.GunsModel
import com.cybernerd404.bgmiguide.model.Videos
import com.cybernerd404.bgmiguide.utils.VideoListener
import kotlinx.android.synthetic.main.guns_item_view.view.*
import kotlinx.android.synthetic.main.item_home_video_layout.view.*

class GunsAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<GunsModel?> = arrayListOf()

    fun setGuns(list: List<GunsModel?>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.guns_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        holder.itemView.gun_name_tv.text = list[position]?.name


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}