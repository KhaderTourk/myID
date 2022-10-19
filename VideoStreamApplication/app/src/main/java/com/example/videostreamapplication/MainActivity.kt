package com.example.videostreamapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val videoURL = "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"

     var player:SimpleExoPlayer?=null

    var playWhenReady = true
    var currentWindow:Int = 0
    var playbackPosition:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // initPlayer()

    }


    private fun initPlayer(){
        player = SimpleExoPlayer.Builder(this).build()
        video_view.player = player

        val mediaItem = MediaItem.fromUri(videoURL)
        player!!.setMediaItem(mediaItem)

        player!!.prepare()
        player!!.playWhenReady = playWhenReady
        player!!.seekTo(currentWindow, playbackPosition)
        /*

        object : YouTubeExtractor(this){
            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, videoMeta: VideoMeta?) {
                if (ytFiles != null){
                    // 720, 1080, 480 (22, 137, 18)
                    val itag = 18 // for 1080p
                    val audioTag = 140 //for m4a audio
                    val videoUrl = ytFiles[itag].url
                    val audioUrl =  ytFiles[audioTag].url



                    val audioSource: MediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(audioUrl))

                    val videoSource: MediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(videoUrl))
                    player!!.setMediaSource(MergingMediaSource(
                            true, videoSource, audioSource
                    ), true)

                    player!!.prepare()
                    player!!.playWhenReady = playWhenReady
                    player!!.seekTo(currentWindow, playbackPosition)
                }
            }
        }.extract(videoURL, false, true)

         */
       }

    private fun releasePlayer(){
        if (player != null) {
            playWhenReady = player!!.playWhenReady
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            player!!.release()
        }
    }

    override fun onStart() {
        super.onStart()
        initPlayer()
    }

    override fun onResume() {
        super.onResume()
//        if (player == null) {
//            initPlayer()
//          //  hideSystemUi()
//        }
    }

//    @SuppressLint("InlinedApi")
//    private fun hideSystemUi() {
//        video_view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
//    }

    override fun onPause() {
        releasePlayer()
        super.onPause()
    }

    override fun onStop() {
        releasePlayer()
        super.onStop()
    }
}