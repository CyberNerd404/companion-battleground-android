package com.cybernerd.bgmiguide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cybernerd.bgmiguide.R
import com.cybernerd.bgmiguide.model.Wallpaper
import com.cybernerd.bgmiguide.model.WallpaperModel
import kotlinx.android.synthetic.main.item_wallpaper_layout.view.*


class WallpaperSliderAdapter(private val context: Context) :
    RecyclerView.Adapter<WallpaperSliderAdapter.ViewHolder>() {
    lateinit var viewPager2: ViewPager2
    var list: List<Wallpaper> = arrayListOf()


    fun setFullWallpaper(list: List<Wallpaper>, viewPager2: ViewPager2) {
        this.list = list
        this.viewPager2 = viewPager2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_wallpaper_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.doOnLayout { // need this to make sure draweeView.width > 0
            Glide.with(context)
                .load(list[position].image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(
                    holder.itemView.width,
                    holder.itemView.height
                ) // resizes the image to these dimensions (in pixel). resize does not respect aspect ratio
                .into(holder.itemView.full_wallpaper_iv)
//            debug("width & height : ${holder.itemView.width} ${holder.itemView.height}")
        }




    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.full_wallpaper_iv

    }


}