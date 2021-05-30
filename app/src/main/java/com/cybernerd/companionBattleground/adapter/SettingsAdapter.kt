package com.cybernerd.companionBattleground.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.utils.SettingListener
import kotlinx.android.synthetic.main.item_home_news_layout.view.*

class SettingsAdapter(private val context: Context, val clickListeners: SettingListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<String> = arrayListOf()

    fun setSettingsText(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_settings_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.title_tv.text = list[position]

        holder.itemView.setOnClickListener {
            clickListeners.settingsListener(position)
        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.title_tv

    }

}