package com.cybernerd.companionBattleground.view.home.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.HomeCardViewAdapter
import com.cybernerd.companionBattleground.model.HomeCardViewModel
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_news.*


class HomeNewsFragment : BaseFragment(), ClickListener {


    lateinit var homeAdapter: HomeCardViewAdapter
    lateinit var homeItemList: MutableList<HomeCardViewModel>
    lateinit var homeModel: HomeCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeCardViewAdapter(requireContext(), this)
        home_news_rv.adapter = homeAdapter

        homeItemList = arrayListOf()
        homeModel = HomeCardViewModel()

        home_news_rv.layoutManager = GridLayoutManager(requireContext(), 1)

        setHomeCardView()
    }

    private fun setHomeCardView() {

        homeItemList.add(HomeCardViewModel("Testing 1", "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"))
        homeItemList.add(HomeCardViewModel("Testing 2", "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"))
        homeItemList.add(HomeCardViewModel("Testing 3", "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"))
        homeItemList.add(HomeCardViewModel("Testing 4", "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"))
        homeItemList.add(HomeCardViewModel("Testing 5", "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"))
        homeItemList.add(HomeCardViewModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "https://images.hdqwalls.com/download/helmet-pubg-4k-2020-r1-1920x1080.jpg"))

        homeAdapter.setHomeCardView(homeItemList)
    }


}