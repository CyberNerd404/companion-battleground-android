package com.cybernerd404.bgmiguide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.model.QuizUIModel
import com.cybernerd404.bgmiguide.model.Videos
import com.cybernerd404.bgmiguide.utils.QuizListener
import com.cybernerd404.bgmiguide.utils.VideoListener
import kotlinx.android.synthetic.main.item_home_video_layout.view.*
import kotlinx.android.synthetic.main.quiz_item_view.view.*

class QuizAdapter(private val context: Context, val quizListener: QuizListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<QuizUIModel?> = arrayListOf()

    fun setQuiz(list: List<QuizUIModel?>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.quiz_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.item_quiz_type.text = list[position]?.name
        holder.itemView.item_background.setImageResource(list[position]!!.image)
        holder.itemView.item_quiz_background.setImageResource(list[position]!!.bg_image)

        holder.itemView.setOnClickListener {
            quizListener.quizListener(list[position]!!)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}