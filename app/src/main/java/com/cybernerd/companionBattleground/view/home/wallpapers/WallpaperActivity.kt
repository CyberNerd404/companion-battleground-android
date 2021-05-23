package com.cybernerd.companionBattleground.view.home.wallpapers

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cybernerd.companionBattleground.R
import kotlinx.android.synthetic.main.activity_wallpaper.*
import java.io.BufferedOutputStream


class WallpaperActivity : AppCompatActivity() {
    var imgLink = ""
    var imageTitle = ""
    lateinit var bitMap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)

        val intent = intent.extras
        imgLink = intent?.getString("imageUrl").toString()
        imageTitle = intent?.getString("imageTitle").toString()

        Glide.with(this)
            .asBitmap()
            .load(imgLink)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    wallpaper_iv_activity.setImageBitmap(resource)
                    download_button.setOnClickListener {
                        addImageToGallery(imageTitle, this@WallpaperActivity, resource)
                    }
//                    holder.itemView.text_background.background = Color.argb(255, redValue, greenValue, blueValue)

                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })

        back_wallpaper.setOnClickListener {
            finish()
        }



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