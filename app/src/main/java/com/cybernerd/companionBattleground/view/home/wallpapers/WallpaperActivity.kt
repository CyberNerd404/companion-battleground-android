package com.cybernerd.companionBattleground.view.home.wallpapers

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.WallpaperSliderAdapter
import com.cybernerd.companionBattleground.model.WallpaperModel
import kotlinx.android.synthetic.main.activity_wallpaper.*
import java.io.BufferedOutputStream


class WallpaperActivity : AppCompatActivity() {
    var imgLink = ""
    var imageTitle = ""
    var wallpaperList: MutableList<WallpaperModel> = arrayListOf()
    var wallpaperSliderAdapter: WallpaperSliderAdapter = WallpaperSliderAdapter(this)

    lateinit var bitMap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)

        val intent = intent.extras
        imgLink = intent?.getString("imageUrl").toString()
        imageTitle = intent?.getString("imageTitle").toString()

        wallpaperList.add(WallpaperModel("http://dreamicus.com/data/yellow/yellow-08.jpg","Hi"))
        wallpaperList.add(WallpaperModel("https://hddesktopwallpapers.in/wp-content/uploads/2015/09/green-macro-flowers.jpg","Hi"))
        wallpaperList.add(WallpaperModel("https://www.drodd.com/images16/green-color6.jpg","Hi"))
        wallpaperList.add(WallpaperModel("https://wonderfulengineering.com/wp-content/uploads/2016/02/mobile-wallpaper-3.jpg","Hi"))
        wallpaperList.add(WallpaperModel("https://wonderfulengineering.com/wp-content/uploads/2016/02/mobile-wallpaper-3.jpg","Hi"))

        viewPager2.adapter = wallpaperSliderAdapter
        wallpaperSliderAdapter.setFullWallpaper(wallpaperList,viewPager2)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPager2.setPageTransformer(compositePageTransformer)

//        Glide.with(this)
//            .asBitmap()
//            .load(imgLink)
//            .into(object : CustomTarget<Bitmap>() {
//                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                    wallpaper_iv_activity.setImageBitmap(resource)
//                    download_button.setOnClickListener {
//                        if(hasWriteStoragePermission()){
//                            addImageToGallery(imageTitle, this@WallpaperActivity, resource)
//                        }
//
//                    }
////                    holder.itemView.text_background.background = Color.argb(255, redValue, greenValue, blueValue)
//
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//                    // this is called when imageView is cleared on lifecycle call or for
//                    // some other reason.
//                    // if you are referencing the bitmap somewhere else too other than this imageView
//                    // clear it here as you can no longer have the bitmap
//                }
//            })

        back_wallpaper.setOnClickListener {
            finish()
        }



    }
    private fun hasWriteStoragePermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return true
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )

                return false
            }
        }

        return true
    }

    fun addImageToGallery(
        fileName: String,
        context: Context,
        bitmap: Bitmap
    ) {

        val values = ContentValues()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
        }
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        values.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, fileName)
        values.put(MediaStore.Images.ImageColumns.TITLE, fileName)

        val uri: Uri? = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )
        uri?.let {
            context.contentResolver.openOutputStream(uri)?.let { stream ->
                val oStream =
                    BufferedOutputStream(stream)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, oStream)
                oStream.close()
            }
        }

    }
}