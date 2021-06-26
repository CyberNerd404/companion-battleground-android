package com.cybernerd404.bgmiguide.view.quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.QuizAdapter
import com.cybernerd404.bgmiguide.model.QuizUIModel
import com.cybernerd404.bgmiguide.utils.QuizListener
import com.cybernerd404.bgmiguide.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_quiz.*


class QuizFragment : BaseFragment(), QuizListener {

    lateinit var quizAdapter: QuizAdapter
    lateinit var list: MutableList<QuizUIModel>

    val viewModel: QuizViewModel by lazy {
        QuizViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = arrayListOf()
        quizAdapter = QuizAdapter(requireContext(), this)

        quiz_rv.adapter = quizAdapter
        quiz_rv.layoutManager = LinearLayoutManager(requireContext())

        var quizUIModel = QuizUIModel()
        quizUIModel.name = "Quess the Weapon"
        quizUIModel.image = R.drawable.bg2
        quizUIModel.bg_image = R.drawable.gallery1
        list.add(0,quizUIModel)

        quizUIModel = QuizUIModel()
        quizUIModel.name = "Quess BGMI items"
        quizUIModel.image = R.drawable.bg1
        quizUIModel.bg_image = R.drawable.gallery5
        list.add(1,quizUIModel)

        quizUIModel = QuizUIModel()
        quizUIModel.name = "Random Quiz"
        quizUIModel.image = R.drawable.bg4
        quizUIModel.bg_image = R.drawable.gallery6
        list.add(2,quizUIModel)

        quizAdapter.setQuiz(list)


    }

    override fun quizListener(quizUIModel: QuizUIModel) {
        when(quizUIModel.name){
            "Quess the Weapon" -> {
                activity.let {
                    Intent(it, WeaponQuizActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
            "Quess BGMI items" -> {
                activity.let {
                    Intent(it, BGMIItemQuizActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
            "Random Quiz" -> {
                activity.let {
                    Intent(it, RandomQuizActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
        }
    }



}