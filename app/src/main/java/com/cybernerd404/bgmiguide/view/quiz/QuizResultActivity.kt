package com.cybernerd404.bgmiguide.view.quiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.utils.Constants
import com.cybernerd404.bgmiguide.view.MainActivity
import kotlinx.android.synthetic.main.activity_quiz_result.*

class QuizResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)
        val correct = intent.getIntExtra(Constants.CORRECT_ANS, 0)

        btn_end.setOnClickListener {
            finish()
        }

        if (correct in 0..3) {
            tv_head.text = "GOOD"
            iv_im.setImageResource(R.drawable.bronze)
        } else if (correct in 4..7) {
            tv_head.text = "NICE"
            iv_im.setImageResource(R.drawable.silver)
        } else {
            tv_head.text = "CONGRATS"
            iv_im.setImageResource(R.drawable.gold)
        }

        val spanable = SpannableString("You gave $correct correct answers\nOut of 10 questions")
        val foregroundColorSpan = ForegroundColorSpan(Color.parseColor("#006400"))
        spanable.setSpan(foregroundColorSpan, 8, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_desc.text = spanable
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}