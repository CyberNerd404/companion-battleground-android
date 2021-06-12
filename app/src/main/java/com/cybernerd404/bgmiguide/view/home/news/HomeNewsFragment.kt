package com.cybernerd404.bgmiguide.view.home.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.HomeNewsAdapter
import com.cybernerd404.bgmiguide.model.HomeNewsListModel
import com.cybernerd404.bgmiguide.model.HomeNewsModel
import com.cybernerd404.bgmiguide.utils.NetworkManager
import com.cybernerd404.bgmiguide.utils.NewsListener
import com.cybernerd404.bgmiguide.utils.debug
import com.cybernerd404.bgmiguide.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_news.*


class HomeNewsFragment : BaseFragment(), NewsListener {


    lateinit var homeAdapter: HomeNewsAdapter
    lateinit var networkManager: NetworkManager


    private val viewModel: HomeNewsViewModel by lazy {
        HomeNewsViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeNewsAdapter(requireContext(), this)
        home_news_rv.adapter = homeAdapter


        home_news_rv.layoutManager = GridLayoutManager(requireContext(), 1)


        networkManager = NetworkManager(requireContext())

        val isAvailable = networkManager.isNetworkAvailable
        debug("isAvailable : $isAvailable")

        viewModel.getNews()
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null)
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
        if (newsResponse != null){
            homeAdapter.setHomeCardView(newsResponse)
        }
    }



    override fun newsListener(homeNewsModel: HomeNewsModel) {
        activity.let {
            Intent(it, NewsActivity::class.java).apply {
                putExtra("homeNewsModel", homeNewsModel)
                startActivity(this)
            }
        }
    }


}