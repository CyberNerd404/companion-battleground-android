package com.cybernerd.companionBattleground.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.utils.ClickListener
import kotlinx.android.synthetic.main.item_home_news_layout.view.*

class HomeNewsAdapter(private val context: Context, val clickListeners: ClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<HomeNewsModel> = arrayListOf()

    fun setHomeCardView(list: List<HomeNewsModel>) {
        this.list = list
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
        holder.itemView.event_iv.setImageResource(list[position].image)
        holder.itemView.title_tv.text = list[position].name

        holder.itemView.setOnClickListener {
            clickListeners.homeNewsClickListener(list[position])
        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.event_iv
        val title = view.title_tv

    }

}