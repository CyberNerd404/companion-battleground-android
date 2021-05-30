package com.cybernerd.companionBattleground.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.model.InformationModel
import com.cybernerd.companionBattleground.utils.ClickListener
import kotlinx.android.synthetic.main.item_information_layout.view.*
import kotlinx.android.synthetic.main.item_wallpaper_layout.view.*

class InformationCategoryAdapter(private val context: Context, val clickListeners: ClickListener) :
    RecyclerView.Adapter<InformationCategoryAdapter.ViewHolder>() {
    lateinit var viewPager2 : ViewPager2
    var list: List<InformationModel> = arrayListOf()


    fun setCategory(list: List<InformationModel>, viewPager2 : ViewPager2) {
        this.list = list
        this.viewPager2 = viewPager2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_information_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {

            0 -> holder.itemView.item_bg.setImageResource(R.drawable.bg1)
            1 -> holder.itemView.item_bg.setImageResource(R.drawable.bg2)
            2 -> holder.itemView.item_bg.setImageResource(R.drawable.bg3)
            3 -> holder.itemView.item_bg.setImageResource(R.drawable.bg4)
            4 -> holder.itemView.item_bg.setImageResource(R.drawable.bg5)
            5 -> holder.itemView.item_bg.setImageResource(R.drawable.bg1)
            else -> {
                holder.itemView.item_bg.setImageResource(R.drawable.bg1)
            }
        }

        holder.itemView.info_feature_iv.setImageResource(list[position].image)
        holder.itemView.info_feature_tv.text = list[position].name

        holder.itemView.setOnClickListener {
            clickListeners.informationCategoryClickListener(position)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }


}