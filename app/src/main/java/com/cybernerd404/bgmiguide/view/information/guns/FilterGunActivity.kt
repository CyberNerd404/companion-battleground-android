package com.cybernerd404.bgmiguide.view.information.guns

import android.os.Bundle
import android.view.View.*
import androidx.appcompat.app.AppCompatActivity
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.FilterAdapter
import com.cybernerd404.bgmiguide.utils.FilterClickListener
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import kotlinx.android.synthetic.main.activity_filter_gun.*


class FilterGunActivity : AppCompatActivity(), FilterClickListener {
    var filterArray = arrayOf("Assault", "DMR", "LMG", "Pistol", "Shotgun", "SMG", "Sniper")
    var sortBasedOnArray = arrayOf("Damage", "Reload", "Rate of Fire", "Range", "KD", "Body Impact")
    lateinit var filterAdapter: FilterAdapter

    var sortByArray = arrayOf("High to Low", "Low to High")
    var defaultSortByArray = arrayOf("High to Low")
    var defaultSortBasedOnArray = arrayOf("Damage")
    lateinit var sortByAdapter: FilterAdapter
    lateinit var sortBasedOnAdapter: FilterAdapter
    var filterValueList: ArrayList<String> = arrayListOf()
    var sortByValueList: ArrayList<String> = arrayListOf()
    var sortBasedOnValueList: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_gun)

        filterValueList.addAll(filterArray)
        sortByValueList.addAll(sortByArray)
        sortBasedOnValueList.addAll(sortBasedOnArray)
        filterAdapter = FilterAdapter(this,this,false)
        filters_rv.adapter = filterAdapter

        val flowLayoutManager = FlowLayoutManager()
        flowLayoutManager.isAutoMeasureEnabled = true
        filters_rv.layoutManager = flowLayoutManager
        filterAdapter.setFilterValues(filterValueList)

        select_all_tv.setOnClickListener {
            filterAdapter.setSelectedFilters(arrayListOf(*filterArray))
            select_all_tv.visibility = GONE
            deselect_all_tv.visibility = VISIBLE
        }
        deselect_all_tv.setOnClickListener{
            filterAdapter.setSelectedFilters(arrayListOf())
            select_all_tv.visibility = VISIBLE
            deselect_all_tv.visibility = GONE
        }

        sortByAdapter = FilterAdapter(this,this,true)
        sort_by_rv.adapter = sortByAdapter
        sortByAdapter.setSelectedFilters(arrayListOf(*defaultSortByArray))
        val flowLayoutSortByManager = FlowLayoutManager()
        flowLayoutSortByManager.isAutoMeasureEnabled = true
        sort_by_rv.layoutManager = flowLayoutSortByManager
        sortByAdapter.setFilterValues(sortByValueList)

        sortBasedOnAdapter = FilterAdapter(this,this,true)
        sort_based_on_rv.adapter = sortBasedOnAdapter
        sortBasedOnAdapter.setSelectedFilters(arrayListOf(*defaultSortBasedOnArray))
        val flowLayoutSortBasedOnyManager = FlowLayoutManager()
        flowLayoutSortBasedOnyManager.isAutoMeasureEnabled = true
        sort_based_on_rv.layoutManager = flowLayoutSortBasedOnyManager
        sortBasedOnAdapter.setFilterValues(sortBasedOnValueList)
    }

    override fun filterListener(position: Int) {
        val list = filterAdapter.getSelectedFilters()
        if(list.isEmpty()){
            select_all_tv.visibility = VISIBLE
            deselect_all_tv.visibility = GONE
        }else{
            select_all_tv.visibility = GONE
            deselect_all_tv.visibility = VISIBLE
        }
    }

}