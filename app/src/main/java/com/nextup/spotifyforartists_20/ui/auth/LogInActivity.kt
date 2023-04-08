package com.nextup.spotifyforartists_20.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.nextup.spotifyforartists_20.R
import com.nextup.spotifyforartists_20.data.models.auth.LoginParams
import com.nextup.spotifyforartists_20.databinding.ActivityLogInBinding
import com.nextup.spotifyforartists_20.domain.android.AndroidUtils
import com.nextup.spotifyforartists_20.domain.android.LoadingDialog
import com.nextup.spotifyforartists_20.domain.android.UiState
import com.nextup.spotifyforartists_20.ui.main.MainActivity
import com.nextup.spotifyforartists_20.ui.view_models.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private val authVm by viewModels<AuthViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        collectLoginState()

        binding.btBecomeAnArtaist.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        binding.btLogin.setOnClickListener {
            if (binding.etEmail.text.toString().isEmpty()){
                AndroidUtils.toast(
                    "Please enter your email address",
                    this
                )
                return@setOnClickListener
            }

            if (binding.etPassword.text.toString().isEmpty()){
                AndroidUtils.toast(
                    "Please enter your password",
                    this
                )
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()){
                AndroidUtils.toast(
                    "Please enter an valid email address",
                    this
                )
                return@setOnClickListener
            }
            LoadingDialog.initLoadingDialog(this)
            authVm.login(LoginParams(binding.etEmail.text.toString(), binding.etPassword.text.toString()))

        }
    }

    private fun collectLoginState() = lifecycleScope.launchWhenStarted {
        authVm.loginState.collectLatest {
            when(it){
                is UiState.Error -> {
                    LoadingDialog.hideLoadingDialog()
                    AndroidUtils.toast(
                        it.message ?: "Please check your password or email address and try again",
                        this@LogInActivity
                    )
                }
                is UiState.Loading -> {
                    LoadingDialog.startLoadingDialog()
                }
                is UiState.Success -> {
                    LoadingDialog.hideLoadingDialog()
                    AndroidUtils.toast(
                        "You have successfully logged in",
                        this@LogInActivity
                    )
                    val intent = Intent(this@LogInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is UiState.Idle -> {}
            }
        }
    }

}