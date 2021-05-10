package com.cybernerd.companionBattleground.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.HomeCardViewAdapter
import com.cybernerd.companionBattleground.model.HomeCardViewModel
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment(), ClickListener {

    lateinit var homeAdapter: HomeCardViewAdapter
    lateinit var homeItemList: MutableList<HomeCardViewModel>
    lateinit var homeModel: HomeCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeCardViewAdapter(requireContext(), this)
        home_rv.adapter = homeAdapter

        homeItemList = arrayListOf()
        homeModel = HomeCardViewModel()

        home_rv.layoutManager = GridLayoutManager(requireContext(), 2)

        setHomeCardView()
    }

    private fun setHomeCardView() {
        homeModel.image = R.drawable.battleground_logo
        homeModel.name = "Testing 1"
        homeItemList.add(homeModel)

        homeModel.image = R.drawable.battleground_logo
        homeModel.name = "Testing 2"
        homeItemList.add(homeModel)

        homeModel.image = R.drawable.battleground_logo
        homeModel.name = "Testing 3"
        homeItemList.add(homeModel)

        homeModel.image = R.drawable.battleground_logo
        homeModel.name = "Testing 4"
        homeItemList.add(homeModel)

        homeModel.image = R.drawable.battleground_logo
        homeModel.name = "Testing 5"
        homeItemList.add(homeModel)

        homeModel.image = R.drawable.battleground_logo
        homeModel.name = "Testing 6"
        homeItemList.add(homeModel)

        homeAdapter.setHomeCardView(homeItemList)
    }
}