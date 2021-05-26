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
import com.cybernerd.companionBattleground.model.*
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.utils.debug
import com.cybernerd.companionBattleground.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_news.*


class HomeNewsFragment : BaseFragment(), ClickListener {


    lateinit var homeAdapter: HomeNewsAdapter

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

        homeAdapter = HomeNewsAdapter(requireContext(), this)
        home_news_rv.adapter = homeAdapter


        home_news_rv.layoutManager = GridLayoutManager(requireContext(), 1)

        viewModel.getNews()
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            debug("HomeFragment : news data = ${it.news}")
            setHomeCardView(it)
        })

        viewModel.showprogress.observe(viewLifecycleOwner, Observer {
            if (it) {
                news_progressbar.visibility = View.VISIBLE
            } else {
                news_progressbar.visibility = View.GONE
            }
        })


    }

    private fun setHomeCardView(newsResponse: HomeNewsListModel) {
        homeAdapter.setHomeCardView(newsResponse)
    }

    override fun homeNewsClickListener(homeNewsModel: HomeNewsModel) {
        activity.let {
            Intent(it, NewsActivity::class.java).apply {
                putExtra("homeNewsModel", homeNewsModel)
                startActivity(this)
            }
        }
    }

    override fun homeVideoClickListener(videos: Videos) {
        TODO("Not yet implemented")
    }

    override fun notificationClickListener(notification: Notification) {
        TODO("Not yet implemented")
    }

    override fun wallpaperClickListener(wallpaperModel: WallpaperModel, position: Int) {
        TODO("Not yet implemented")
    }

    override fun informationCategoryClickListener() {
        TODO("Not yet implemented")
    }

    override fun settingsClickListener(position: Int) {
        TODO("Not yet implemented")
    }


}