package com.nextup.spotifyforartists_20.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.nextup.spotifyforartists_20.data.models.auth.RegisterParams
import com.nextup.spotifyforartists_20.databinding.ActivityRegisterBinding
import com.nextup.spotifyforartists_20.domain.android.AndroidUtils
import com.nextup.spotifyforartists_20.domain.android.LoadingDialog
import com.nextup.spotifyforartists_20.domain.android.UiState
import com.nextup.spotifyforartists_20.ui.main.MainActivity
import com.nextup.spotifyforartists_20.ui.view_models.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val authVm by viewModels<AuthViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        collectRegisterState()

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btCreate.setOnClickListener {
            if (binding.etEmail.text.toString().isEmpty()){
                AndroidUtils.toast(
                    "Please enter your email address",
                    this
                )
                return@setOnClickListener
            }
            if (binding.etName.text.toString().isEmpty()){
                AndroidUtils.toast(
                    "Please enter your name",
                    this
                )
                return@setOnClickListener
            }
            if (binding.etLastname.text.toString().isEmpty()){
                AndroidUtils.toast(
                    "Please enter your lastname",
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

            val registerParams = RegisterParams(
                binding.etName.text.toString(),
                binding.etLastname.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
                )

            LoadingDialog.initLoadingDialog(this)
            authVm.register(registerParams)

        }


    }

    private fun collectRegisterState() = lifecycleScope.launchWhenStarted {
        authVm.registerState.collectLatest {
            when(it){
                is UiState.Error -> {
                    LoadingDialog.hideLoadingDialog()
                    AndroidUtils.toast(
                        it.message!!,
                        this@RegisterActivity
                    )
                }
                is UiState.Loading -> {
                    LoadingDialog.startLoadingDialog()
                }
                is UiState.Success -> {
                    LoadingDialog.hideLoadingDialog()
                    AndroidUtils.toast(
                        "Your account has been successfully created",
                        this@RegisterActivity
                    )
                    onBackPressedDispatcher.onBackPressed()
                }
                is UiState.Idle -> {}
            }
        }
    }


}