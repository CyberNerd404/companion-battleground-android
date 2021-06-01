package com.cybernerd.bgmiguide.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cybernerd.bgmiguide.R
import com.cybernerd.bgmiguide.model.Wallpaper
import com.cybernerd.bgmiguide.model.WallpapersModel
import com.cybernerd.bgmiguide.utils.WallpaperListener
import kotlinx.android.synthetic.main.item_home_news_layout.view.*
import kotlinx.android.synthetic.main.item_theme_grid_layout.view.*


class ThemeAdapter(private val context: Context, val clickListeners: WallpaperListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<Wallpaper> = arrayListOf()

    fun setWallpaperGrid(list: List<Wallpaper>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_theme_grid_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(this.context)
            .asBitmap()
            .load(list[position].image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    holder.itemView.wallpaper_iv.setImageBitmap(resource)

                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })

        holder.itemView.setOnClickListener {
            clickListeners.wallpaperListener(WallpapersModel(list), position)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.event_iv
        val title = view.title_tv

    }

}