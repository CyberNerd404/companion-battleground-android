package com.cybernerd404.bgmiguide.view.home.wallpapers

import android.Manifest
import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.WallpaperSliderAdapter
import com.cybernerd404.bgmiguide.model.WallpaperModel
import com.cybernerd404.bgmiguide.model.WallpapersModel
import com.cybernerd404.bgmiguide.utils.ActionBottomSheetDialog
import com.cybernerd404.bgmiguide.utils.showToast
import kotlinx.android.synthetic.main.activity_wallpaper.*
import java.io.BufferedOutputStream


class WallpaperActivity : AppCompatActivity(),ActionBottomSheetDialog.ItemClickListener {
    var imgLink = ""
    var imageTitle = ""
    var position = -1
    var wallpaperList: MutableList<WallpaperModel> = arrayListOf()
    var wallpaperSliderAdapter: WallpaperSliderAdapter = WallpaperSliderAdapter(this)
    lateinit var wallpaperManager: WallpaperManager
    lateinit var wallpaperObj : WallpapersModel

    lateinit var bitMap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)

        val intent = intent.extras
        position = intent?.getInt("positionWallpaper") ?: -1


        wallpaperObj = intent?.getSerializable("wallpaperObj") as WallpapersModel

        if (wallpaperObj != null) {
            imgLink = wallpaperObj.wallpapers[position].image
        }


        viewPager2.adapter = wallpaperSliderAdapter
        wallpaperObj?.let { wallpaperSliderAdapter.setFullWallpaper(it.wallpapers, viewPager2) }
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        wallpaperManager = WallpaperManager.getInstance(this)


        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.currentItem = position

        download_button.setOnClickListener {
            Glide.with(this)
                .asBitmap()
                .load(wallpaperObj.wallpapers[viewPager2.currentItem].image)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?,
                    ) {
                        if (hasWriteStoragePermission()) {
                            wallpaper_progress_bar.visibility = View.VISIBLE
                            addImageToGallery(imageTitle, this@WallpaperActivity, resource)
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })
        }
        set_wallpaper_iv.setOnClickListener(View.OnClickListener {
            val openBottomSheet = ActionBottomSheetDialog.newInstance()
            openBottomSheet.show(supportFragmentManager, ActionBottomSheetDialog.TAG)
        })


        back_wallpaper.setOnClickListener {
            finish()
        }


    }


    private fun hasWriteStoragePermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return true
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
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
        bitmap: Bitmap,
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
        showToast(context, "Image Downloaded Successfully")
        wallpaper_progress_bar.visibility = View.GONE

    }

    override fun onItemClick(item: String?) {

        Glide.with(this)
            .asBitmap()
            .load(wallpaperObj.wallpapers[viewPager2.currentItem].image)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    when (item) {
                        "Set as Home Screen" -> {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                wallpaperManager.setBitmap(resource,
                                    null,
                                    true,
                                    WallpaperManager.FLAG_SYSTEM)
                                showToast(this@WallpaperActivity, "Home Wallpaper Set Successfully")
                            }

                        }
                        "Set as Lock Screen" -> {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                wallpaperManager.setBitmap(resource,
                                    null,
                                    true,
                                    WallpaperManager.FLAG_LOCK)
                                showToast(this@WallpaperActivity, "Lock Screen Wallpaper Set Successfully")
                            }

                        }
                        else -> {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                wallpaperManager.setBitmap(resource,
                                    null,
                                    true,
                                    WallpaperManager.FLAG_LOCK)
                                wallpaperManager.setBitmap(resource,
                                    null,
                                    true,
                                    WallpaperManager.FLAG_SYSTEM)

                                showToast(this@WallpaperActivity, "Wallpaper Set Successfully")
                            }
                        }
                    }

                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })
    }
}