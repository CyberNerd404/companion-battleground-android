package com.cybernerd.bgmiguide.view.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd.bgmiguide.R
import com.cybernerd.bgmiguide.adapter.NotificationAdapter
import com.cybernerd.bgmiguide.model.Notification
import com.cybernerd.bgmiguide.utils.NotificationListener
import com.cybernerd.bgmiguide.utils.showToast
import com.cybernerd.bgmiguide.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_notification.*


class NotificationFragment : BaseFragment(), NotificationListener {

    lateinit var notificationAdapter: NotificationAdapter
    val viewModel: NotificationViewModel by lazy {
        NotificationViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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

    override fun notificationListener(notification: Notification) {
        showToast(requireContext(), "Go to Home Screen")
    }

}