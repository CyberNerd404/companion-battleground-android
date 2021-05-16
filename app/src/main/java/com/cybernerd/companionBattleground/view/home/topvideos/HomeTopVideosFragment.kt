package com.cybernerd.companionBattleground.view.home.topvideos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.HomeVideoAdapter
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.Videos
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.utils.debug
import com.cybernerd.companionBattleground.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_top_videos.*

class HomeTopVideosFragment : BaseFragment(), ClickListener {

    lateinit var homeAdapter: HomeVideoAdapter
    private val viewModel: HomeVideoViewModel by lazy {
        HomeVideoViewModel()
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

        viewModel.getVideo()
        viewModel.videoLiveData.observe(viewLifecycleOwner, Observer {
            debug("HomeFragment : video data = ${it.videos}")
        })


        homeAdapter = HomeVideoAdapter(viewLifecycleOwner, requireContext(), this)
        home_videos_rv.adapter = homeAdapter


        home_videos_rv.layoutManager = LinearLayoutManager(requireContext())

        setHomeVideos()

    }

    private fun setHomeVideos() {

    }

    override fun homeNewsClickListener(homeNewsModel: HomeNewsModel) {
        TODO("Not yet implemented")
    }

    override fun homeVideoClickListener(video: Videos) {

    }


}