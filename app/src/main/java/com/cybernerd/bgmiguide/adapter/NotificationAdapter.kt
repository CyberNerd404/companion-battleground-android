package com.cybernerd.bgmiguide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cybernerd.bgmiguide.R
import com.cybernerd.bgmiguide.model.Notification
import com.cybernerd.bgmiguide.model.NotificationModel
import com.cybernerd.bgmiguide.utils.NotificationListener
import kotlinx.android.synthetic.main.item_home_news_layout.view.*
import kotlinx.android.synthetic.main.item_notification_layout.view.*

class NotificationAdapter(private val context: Context, val clickListeners: NotificationListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<Notification> = arrayListOf()


    fun setNotification(response: NotificationModel) {
        list = response.notifications
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_notification_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(this.context)
            .load(list[position].thumbnail)
            .placeholder(R.drawable.battleground_logo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.notification_event_iv)

        holder.itemView.notification_title_tv.text = list[position].title
        holder.itemView.notification_description_tv.text = list[position].description

        holder.itemView.setOnClickListener {
            clickListeners.notificationListener(list[position])
        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.event_iv
        val title = view.title_tv

    }

}