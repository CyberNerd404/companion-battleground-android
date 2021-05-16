package com.cybernerd.companionBattleground.view.home.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.HomeNewsAdapter
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.Videos
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.utils.debug
import com.cybernerd.companionBattleground.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_news.*


class HomeNewsFragment : BaseFragment(), ClickListener {


    lateinit var homeAdapter: HomeNewsAdapter
    lateinit var homeItemList: MutableList<HomeNewsModel>
    lateinit var homeModel: HomeNewsModel

    private val viewModel: HomeNewsViewModel by lazy {
        HomeNewsViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNews()
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            debug("HomeFragment : news data = ${it.news}")

        })


        homeAdapter = HomeNewsAdapter(requireContext(), this)
        home_news_rv.adapter = homeAdapter

        homeItemList = arrayListOf()
        homeModel = HomeNewsModel()

        home_news_rv.layoutManager = GridLayoutManager(requireContext(), 1)

        setHomeCardView()
    }

    private fun setHomeCardView() {
        homeModel.image = "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"
        homeModel.name = "Testing 1"
        homeItemList.add(0, homeModel)

        homeModel = HomeNewsModel()
        homeModel.image = "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"
        homeModel.name = "Testing 2"
        homeItemList.add(1, homeModel)

        homeModel = HomeNewsModel()
        homeModel.image = "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"
        homeModel.name = "Testing 3"
        homeItemList.add(2, homeModel)

        homeModel = HomeNewsModel()
        homeModel.image = "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"
        homeModel.name = "Testing 4"
        homeItemList.add(3, homeModel)

        homeModel = HomeNewsModel()
        homeModel.image = "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"
        homeModel.name = "Testing 5"
        homeItemList.add(4, homeModel)

        homeModel = HomeNewsModel()
        homeModel.image ="https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"
        homeModel.name = "Testing 6"
        homeItemList.add(5, homeModel)

        debug(" size : ${homeItemList.size} homeItemList:$homeItemList")

        homeAdapter.setHomeCardView(homeItemList)
    }

    override fun homeNewsClickListener(homeNewsModel: HomeNewsModel) {
        activity.let {
            Intent(it, NewsActivity::class.java).apply {
                putExtra("name", homeNewsModel.name)
                putExtra("img", homeNewsModel.image)
                startActivity(this)
            }
        }
    }

    override fun homeVideoClickListener(videos: Videos) {
        TODO("Not yet implemented")
    }


}