package com.cybernerd.companionBattleground.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.model.HomeVideoModel
import com.cybernerd.companionBattleground.utils.ClickListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import kotlinx.android.synthetic.main.item_home_video_layout.view.*

class HomeVideoAdapter(val lifecycleOwner: LifecycleOwner,private val context: Context, val clickListeners: ClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<HomeVideoModel> = arrayListOf()
    var isSelected = false

    fun setHomeVideo(list: List<HomeVideoModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_video_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.youtube_title_tv.text = list[position].youtuebeTitle

        lifecycleOwner.lifecycle.addObserver(holder.itemView.youtube_player_view)
        holder.itemView.youtube_player_view.addYouTubePlayerListener(object : YouTubePlayerListener {
            override fun onApiChange(youTubePlayer: YouTubePlayer) {
                TODO("Not yet implemented")
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                TODO("Not yet implemented")
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
//                youTubePlayer.loadVideo(list[position].youtubeId,0f)

            }

            override fun onPlaybackQualityChange(
                youTubePlayer: YouTubePlayer,
                playbackQuality: PlayerConstants.PlaybackQuality
            ) {
//                youTubePlayer.loadVideo(list[position].youtubeId,0f)

            }

            override fun onPlaybackRateChange(
                youTubePlayer: YouTubePlayer,
                playbackRate: PlayerConstants.PlaybackRate
            ) {
//                youTubePlayer.loadVideo(list[position].youtubeId,0f)

            }

            override fun onReady(youTubePlayer: YouTubePlayer) {
                if(isSelected){
                    youTubePlayer.loadVideo(list[position].youtubeId, 0F)
                }
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
//                youTubePlayer.loadVideo(list[position].youtubeId,0f)

            }

            override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
//                youTubePlayer.loadVideo(list[position].youtubeId,0f)

            }

            override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
//                youTubePlayer.loadVideo(list[position].youtubeId,0f)
            }

            override fun onVideoLoadedFraction(
                youTubePlayer: YouTubePlayer,
                loadedFraction: Float
            ) {
//                youTubePlayer.loadVideo(list[position].youtubeId,0f)

            }

        })

        holder.itemView.setOnClickListener {
            clickListeners.homeVideoClickListener(list[position])
            isSelected = true
            notifyDataSetChanged()
        }

    }


     class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val youtubeId = view.youtube_player_view
        val title = view.youtube_title_tv

    }

}