package com.cybernerd.companionBattleground.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.utils.ClickListener

class InformationCategoryAdapter(private val context: Context, val clickListeners: ClickListener) :
    RecyclerView.Adapter<InformationCategoryAdapter.ViewHolder>() {
    lateinit var viewPager2 : ViewPager2
    var list: List<String> = arrayListOf()


    fun setCategory(list: List<String>, viewPager2 : ViewPager2) {
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
        holder.itemView.setOnClickListener {
            clickListeners.informationCategoryClickListener()
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }


}