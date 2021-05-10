package com.cybernerd.companionBattleground.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
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
        holder.itemView.event_iv.setImageResource(R.drawable.battleground_logo)
        holder.itemView.title_tv.text = list[position].name

    }


    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val image = view.event_iv
        val title = view.title_tv

    }

}