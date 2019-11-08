package com.edgar.mercadosearch.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.edgar.mercadosearch.R
import com.edgar.mercadosearch.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_spalsh.*

/**
 * @author Edgar Glellel
 * An Splash full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar).
 */
class SpalshActivity : AppCompatActivity() {

    val background = object : Thread() {
        override fun run() {
            try {
                // Thread will sleep for 3 seconds
                sleep((3 * 1000).toLong())

                // After 3 seconds redirect to another intent
                val i = Intent(baseContext, HomeActivity::class.java)
                startActivity(i)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_spalsh)

        //Set full screen programmatically
        fullscreen_content.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //Start Thread
        background.start()
    }

}
