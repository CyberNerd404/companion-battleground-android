package com.cybernerd.companionBattleground.view.home.topvideos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.HomeNewsAdapter
import com.cybernerd.companionBattleground.adapter.HomeVideoAdapter
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.HomeVideoModel
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.utils.debug
import com.cybernerd.companionBattleground.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_news.*
import kotlinx.android.synthetic.main.fragment_home_top_videos.*

class HomeTopVideosFragment : BaseFragment(), ClickListener {

    lateinit var homeAdapter: HomeVideoAdapter
    lateinit var homeItemList: MutableList<HomeVideoModel>
    lateinit var homeModel: HomeVideoModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_top_videos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        homeAdapter = HomeVideoAdapter(viewLifecycleOwner,requireContext(), this)
        home_videos_rv.adapter = homeAdapter

        homeItemList = arrayListOf()
        homeModel = HomeVideoModel()

        home_videos_rv.layoutManager = LinearLayoutManager(requireContext())

        setHomeVideos()

    }

    private fun setHomeVideos() {
        homeModel.youtubeId = "5INZBeuaETg"
        homeModel.youtuebeTitle = "Testing 1"
        homeItemList.add(0, homeModel)

        /*homeModel = HomeVideoModel()
        homeModel.youtubeId = "RMWVftPRGro"
        homeModel.youtuebeTitle = "Testing 2"
        homeItemList.add(1, homeModel)

        homeModel = HomeVideoModel()
        homeModel.youtubeId = "RMWVftPRGro"
        homeModel.youtuebeTitle = "Testing 3"
        homeItemList.add(2, homeModel)

        homeModel = HomeVideoModel()
        homeModel.youtubeId = "RMWVftPRGro"
        homeModel.youtuebeTitle = "Testing 4"
        homeItemList.add(3, homeModel)

        homeModel = HomeVideoModel()
        homeModel.youtubeId = "RMWVftPRGro"
        homeModel.youtuebeTitle = "Testing 5"
        homeItemList.add(4, homeModel)

        homeModel = HomeVideoModel()
        homeModel.youtubeId = "RMWVftPRGro"
        homeModel.youtuebeTitle = "Testing 6"
        homeItemList.add(5, homeModel)
*/
        debug(" size : ${homeItemList.size} homeItemList:$homeItemList")

        homeAdapter.setHomeVideo(homeItemList)
    }

    override fun homeNewsClickListener(homeNewsModel: HomeNewsModel) {
        TODO("Not yet implemented")
    }

    override fun homeVideoClickListener(homeVideoModel: HomeVideoModel) {
        debug("videoId : ${homeVideoModel.youtubeId} & videoTitle : ${homeVideoModel.youtuebeTitle}")
    }


}