package com.cybernerd.companionBattleground.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.model.WallpaperModel
import kotlinx.android.synthetic.main.item_wallpaper_layout.view.*


class WallpaperSliderAdapter(private val context: Context) :
    RecyclerView.Adapter<WallpaperSliderAdapter.ViewHolder>() {
    lateinit var viewPager2 : ViewPager2
    var list: List<WallpaperModel> = arrayListOf()


    fun setFullWallpaper(list: List<WallpaperModel>, viewPager2 : ViewPager2) {
        this.list = list
        this.viewPager2 = viewPager2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_wallpaper_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(this.context)
            .asBitmap()
            .load(list[position].imageLink)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    holder.itemView.full_wallpaper_iv.setImageBitmap(resource)

//                    holder.itemView.text_background.background = Color.argb(255, redValue, greenValue, blueValue)

                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.full_wallpaper_iv

    }


}