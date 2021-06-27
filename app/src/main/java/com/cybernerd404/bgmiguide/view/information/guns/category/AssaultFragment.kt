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
import com.cybernerd404.bgmiguide.model.GunsModelItem
import com.cybernerd404.bgmiguide.view.information.guns.GunsViewModel
import kotlinx.android.synthetic.main.fragment_assault.*


class AssaultFragment : Fragment() {

    lateinit var gunsAdapter: GunsAdapter

    val viewModel: GunsViewModel by lazy {
        GunsViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assault, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gunsAdapter = GunsAdapter(requireContext())

        guns_rv.adapter = gunsAdapter
        guns_rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getGuns()
        viewModel.gunsLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null)
                gunsAdapter.setGuns(it)
        })
    }

}