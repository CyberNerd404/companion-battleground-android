package com.cybernerd404.bgmiguide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.utils.FilterClickListener
import kotlinx.android.synthetic.main.item_filter_list.view.*

class FilterAdapter(private val context: Context, val clickListener: FilterClickListener, val singleSelection: Boolean) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list = arrayListOf<String>()
    var selectedList = arrayListOf<String>()

    fun setFilterValues(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filter_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setSelectedFilters(selectedList: ArrayList<String>){
        this.selectedList = selectedList
        notifyDataSetChanged()
    }

    fun getSelectedFilters(): ArrayList<String>{
        return selectedList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.selected_tv.text = list[position]
        holder.itemView.filter_unselect.text = list[position]

        if(selectedList.contains(list[position])){
            holder.itemView.filter_unselect.visibility = GONE
            holder.itemView.filter_select.visibility = VISIBLE
        }else{
            holder.itemView.filter_select.visibility = GONE
            holder.itemView.filter_unselect.visibility = VISIBLE
        }
        holder.itemView.setOnClickListener {
            if(!selectedList.contains(list[position])){
                if(!singleSelection){
                    selectedList.add(list[position])
                    notifyDataSetChanged()
                }else{
                    selectedList = arrayListOf<String>()
                    selectedList.add(list[position])
                    notifyDataSetChanged()
                }
            }else{
                if(!singleSelection){
                    selectedList.remove(list[position])
                    notifyDataSetChanged()
                }else{
                    if(selectedList.size != 1){
                        selectedList.remove(list[position])
                        notifyDataSetChanged()
                    }
                }
            }
            if(!singleSelection) {
                clickListener.filterListener(position)
            }

        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}