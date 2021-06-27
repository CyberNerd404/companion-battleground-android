package com.cybernerd404.bgmiguide.view.information.guns.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.GunsAdapter
import com.cybernerd404.bgmiguide.view.information.guns.GunsViewModel
import kotlinx.android.synthetic.main.fragment_assault.*

class DMRFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_d_m_r, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}