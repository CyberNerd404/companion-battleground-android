package com.cybernerd.companionBattleground.view.home.topvideos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.utils.debug
import com.cybernerd.companionBattleground.utils.validate
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class VideoActivity : AppCompatActivity() {

    lateinit var youtubePlayer: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val intent = intent.extras

        val vId = intent?.getString("videoId")
        validate("vId : $vId")

        youtubePlayer = findViewById(R.id.youtube_player_view)

        lifecycle.addObserver(youtubePlayer)

        youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                if (vId != null) {
                    validate("loadVideo: $vId")
                    youTubePlayer.loadVideo(vId, 0f)
                    youTubePlayer.play()
                }
            }
        })
    }


}