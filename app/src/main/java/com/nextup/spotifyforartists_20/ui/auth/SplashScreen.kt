package com.nextup.spotifyforartists_20.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nextup.spotifyforartists_20.R
import com.nextup.spotifyforartists_20.domain.firebase.UserSessionChecker
import com.nextup.spotifyforartists_20.domain.user_prefrences.DataStore
import com.nextup.spotifyforartists_20.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {


    @Inject
    lateinit var userSessionChecker: UserSessionChecker


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        if (userSessionChecker.check() == UserSessionChecker.USER_IS_LOGGED_IN){
            startActivity(MainActivity())
        }else{
            startActivity(LogInActivity())
        }



    }

    private fun startActivity(activity: AppCompatActivity){
        Handler().postDelayed(
            {
                val intent = Intent(this, activity::class.java)
                startActivity(intent)
                finish()
            }, 2000
        )
    }


}