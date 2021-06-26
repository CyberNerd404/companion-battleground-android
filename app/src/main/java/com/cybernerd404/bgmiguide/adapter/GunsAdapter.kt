package com.cybernerd404.bgmiguide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.model.GunsModelX
import kotlinx.android.synthetic.main.guns_item_view.view.*

class GunsAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list = GunsModelX()

    fun setGuns(list: GunsModelX) {
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

        holder.itemView.gun_name_tv.text = list[position].name
        holder.itemView.guns_type_name.text = list[position].type
        holder.itemView.damage_progress_bar.progress = list[position].damage.base
        holder.itemView.damage_value_tv.text = list[position].damage.base.toString()
        holder.itemView.reload_progress_text.progress = list[position].reload
        holder.itemView.reload_value_tv.text = list[position].reload.toString()
        holder.itemView.rate_of_fire_value_tv.text = list[position].rate.toString()
        holder.itemView.rate_of_fire_progress_bar.progress = list[position].rate
        holder.itemView.range_value_tv.text = list[position].range.toString()
        holder.itemView.range_progress_bar.progress = list[position].range
        holder.itemView.kd_value_tv.text = list[position].capacity.toString()
        holder.itemView.kd_progress_bar.progress = list[position].capacity
        holder.itemView.body_implement_value_tv.text = list[position].damage.chest0.toString()
        holder.itemView.body_implement_progress_bar.progress = list[position].damage.chest0





    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}