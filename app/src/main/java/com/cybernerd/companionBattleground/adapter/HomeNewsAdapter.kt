package com.cybernerd.companionBattleground.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.model.HomeNewsListModel
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.utils.ClickListener
import kotlinx.android.synthetic.main.item_home_news_layout.view.*

class HomeNewsAdapter(private val context: Context, val clickListeners: ClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<HomeNewsModel> = arrayListOf()
    var response: HomeNewsListModel = HomeNewsListModel(list)

    fun setHomeCardView(response: HomeNewsListModel) {
        this.response = response
        list = response.news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_news_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(this.context)
            .load(list[position].media)
            .placeholder(R.drawable.battleground_logo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.event_iv)
        holder.itemView.title_tv.text = list[position].title

        holder.itemView.setOnClickListener {
            clickListeners.homeNewsClickListener(list[position])
        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.event_iv
        val title = view.title_tv

    }

}