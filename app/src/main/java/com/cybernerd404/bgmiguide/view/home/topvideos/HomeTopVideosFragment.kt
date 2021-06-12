package com.cybernerd404.bgmiguide.view.home.topvideos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.HomeVideoAdapter
import com.cybernerd404.bgmiguide.model.Videos
import com.cybernerd404.bgmiguide.utils.VideoListener
import com.cybernerd404.bgmiguide.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_top_videos.*

class HomeTopVideosFragment : BaseFragment(), VideoListener {

    lateinit var homeAdapter: HomeVideoAdapter
    private val viewModel: HomeVideoViewModel by lazy {
        HomeVideoViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_top_videos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeVideoAdapter(requireContext(), this)
        home_videos_rv.adapter = homeAdapter


        home_videos_rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getVideo()
        viewModel.videoLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                homeAdapter.setHomeVideo(it.videos)
            }
        })

        viewModel.showprogress.observe(viewLifecycleOwner, Observer {
            if (it) {
                video_progressbar.visibility = VISIBLE
            } else {
                video_progressbar.visibility = GONE
            }
        })


    }


    override fun videoListener(videoMode: Videos) {
        activity.let {
            Intent(it, VideoActivity::class.java).apply {
                putExtra("videoId", videoMode.videoId)
                startActivity(this)
            }
        }    }

}