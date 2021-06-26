package com.cybernerd404.bgmiguide.view.information

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.cybernerd404.bgmiguide.ComingSoonActivity
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.InformationCategoryAdapter
import com.cybernerd404.bgmiguide.model.InformationModel
import com.cybernerd404.bgmiguide.utils.InformationListener
import com.cybernerd404.bgmiguide.view.BaseFragment
import com.cybernerd404.bgmiguide.view.information.guns.GunsActivity
import com.cybernerd404.bgmiguide.view.information.maps.MapsActivity
import kotlinx.android.synthetic.main.activity_wallpaper.*

class InformationFragment : BaseFragment(), InformationListener {

    var categoryList: MutableList<String> = arrayListOf()
    lateinit var categoryAdapter: InformationCategoryAdapter
    lateinit var infoModel: InformationModel
    lateinit var infoList: MutableList<InformationModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        infoModel = InformationModel()
        infoList = arrayListOf()

        categoryAdapter = InformationCategoryAdapter(requireContext(),this)

        infoList.add(0,InformationModel("Map",R.drawable.gallery5))
        infoList.add(1,InformationModel("Guns",R.drawable.gallery1))
        infoList.add(2,InformationModel("Tips&Tricks",R.drawable.gallery6))
        infoList.add(3,InformationModel("Top Ranking",R.drawable.gallery7))
        infoList.add(4,InformationModel("Damage Calculator",R.drawable.gallery1))
        infoList.add(5,InformationModel("Sound",R.drawable.gallery5))



        /*categoryList.add("text1")
        categoryList.add("text2")
        categoryList.add("text3")
        categoryList.add("text4")
        categoryList.add("text5")
        categoryList.add("text6")*/

        viewPager2.adapter = categoryAdapter
        categoryAdapter.setCategory(infoList,viewPager2)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleX = 0.85f + r * 0.15f
        }
        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL


    }


    override fun informationCategoryListener(position: Int) {
        activity.let {
            Intent(it, GunsActivity::class.java).apply {
                putExtra("position", position)
                startActivity(this)
            }
        }
    }


}