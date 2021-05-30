package com.cybernerd.companionBattleground.view.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.NotificationAdapter
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.Notification
import com.cybernerd.companionBattleground.model.Videos
import com.cybernerd.companionBattleground.model.WallpaperModel
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_notification.*


class NotificationFragment : BaseFragment(), ClickListener {

    lateinit var notificationAdapter: NotificationAdapter
    val viewModel: NotificationViewModel by lazy {
        NotificationViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationAdapter = NotificationAdapter(requireContext(), this)
        notification_rv.adapter = notificationAdapter


        notification_rv.layoutManager = GridLayoutManager(requireContext(), 1)

        viewModel.getNotifications()
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            notificationAdapter.setNotification(it)
        })

        viewModel.showprogress.observe(viewLifecycleOwner, Observer {
            if (it) {
                noti_progressbar.visibility = View.VISIBLE
            } else {
                noti_progressbar.visibility = View.GONE
            }
        })


    }

    override fun homeNewsClickListener(homeNewsModel: HomeNewsModel) {
        TODO("Not yet implemented")
    }

    override fun homeVideoClickListener(videoMode: Videos) {
        TODO("Not yet implemented")
    }

    override fun settingsClickListener(position: Int) {
        TODO("Not yet implemented")
    }

    override fun notificationClickListener(notification: Notification) {

        /*when (notification.type) {
            "video" -> {
                Intent(requireContext(), VideoActivity::class.java).apply {
                    putExtra("videoId", notification._id)
                    startActivity(this)
                }
            }

            "news" -> {
                Intent(requireContext(), NewsActivity::class.java).apply {
                    startActivity(this)
                }
            }

        }*/


    }

    override fun wallpaperClickListener(wallpaperModel: WallpaperModel, position: Int) {
        TODO("Not yet implemented")
    }

    override fun informationCategoryClickListener(position: Int) {
        TODO("Not yet implemented")
    }

}