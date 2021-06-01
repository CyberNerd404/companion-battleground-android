package com.solum_ble_mesh.iot.utils.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.cybernerd.bgmiguide.R

class SetWallpaperDialog: DialogFragment() {


    companion object {

        const val TAG = "SetWallpaper"
        fun newInstance(bitmap: Bitmap): SetWallpaperDialog {
            val args = Bundle()
//            args.put(bitmap)
            val fragment = SetWallpaperDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.set_wallpaper_item_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}