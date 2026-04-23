package com.example.cloneinstagram

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var likectr = 0

        val likebtn = findViewById<Button>(R.id.btnLike)
        val likeCount = findViewById<TextView>(R.id.like_count)

        val combtn = findViewById<Button>(R.id.btnComment)

        val follow = findViewById<Button>(R.id.btn_follow)

        val videoView = findViewById<VideoView>(R.id.videoView)

        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
        }

        likebtn.setOnClickListener {

            likebtn.isSelected = !likebtn.isSelected

            if (likebtn.isSelected) {
                likectr++
                animateHeartFlash()
            } else {
                likectr--
            }

            likeCount.text = likectr.toString()
        }


        combtn.setOnClickListener {
            combtn.isSelected = !combtn.isSelected
        }

        follow.setOnClickListener {
            follow.isSelected = !follow.isSelected
            //follow.text = if (follow.isSelected) "Following" else "Follow"
            animateFollow()
        }

        val videoPath = "android.resource://" + packageName + "/" + R.raw.videobg2
        videoView.setVideoURI(videoPath.toUri())
        videoView.setZOrderOnTop(false)
        videoView.start()

    }

    private fun animateHeartFlash() {
        val heart = findViewById<ImageView>(R.id.imgFlashHeart)
        heart.visibility = View.VISIBLE
        heart.alpha = 1.0f
        heart.scaleX = 0f
        heart.scaleY = 0f

        heart.animate()
            .scaleX(0.7f)
            .scaleY(0.7f)
            .setDuration(250)
            .withEndAction {
                heart.animate()
                    .scaleX(0f)
                    .scaleY(0f)
                    .alpha(0f)
                    .setDuration(250)
                    .withEndAction {
                        heart.visibility = View.INVISIBLE
                    }
                    .start()
            }
            .start()
    }

    private fun animateFollow() {
        val thumb = findViewById<ImageView>(R.id.imgFlashFollow)
        thumb.visibility = View.VISIBLE
        thumb.alpha = 1.0f
        thumb.scaleX = 0f
        thumb.scaleY = 0f

        thumb.animate()
            .scaleX(0.7f)
            .scaleY(0.7f)
            .setDuration(250)
            .withEndAction {
                thumb.animate()
                    .scaleX(0f)
                    .scaleY(0f)
                    .alpha(0f)
                    .setDuration(250)
                    .withEndAction {
                        thumb.visibility = View.INVISIBLE
                    }
                    .start()
            }
            .start()
    }


}
