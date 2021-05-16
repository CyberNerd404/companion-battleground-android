package com.cybernerd.companionBattleground.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.model.HomeCardViewModel
import com.cybernerd.companionBattleground.utils.ClickListener
import kotlinx.android.synthetic.main.home_item_layout.view.*


class HomeCardViewAdapter (private val context: Context, val clickListeners: ClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var list: List<HomeCardViewModel> = arrayListOf()

    fun setHomeCardView(list: List<HomeCardViewModel>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.home_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(this.context)
            .load(list[position].image)
            .placeholder(R.drawable.battleground_logo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.event_iv)
        holder.itemView.title_tv.text = list[position].name

    }


    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val image = view.event_iv
        val title = view.title_tv


    }

}

