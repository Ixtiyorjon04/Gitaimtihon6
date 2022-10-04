package uz.gita.gitaimtihon6samandar.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import uz.gita.gitaimtihon6samandar.R
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.SplashScreenViewModel
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl.SplashScreenViewModelImpl

class SplashScreen: Fragment(R.layout.screen_splash) {
    private val viewModel:SplashScreenViewModel by viewModels<SplashScreenViewModelImpl> ()
    private val navController :NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openSignInScreenLiveData.observe(this){
            navController.navigate(SplashScreenDirections.actionSplashScreenToSignInScreen())
        }
        viewModel.openIntroScreenLiveData.observe(this){
            navController.navigate(SplashScreenDirections.actionSplashScreenToIntroScreen())
        }
        viewModel.openMainScreenLiveData.observe(this){
            navController.navigate(SplashScreenDirections.actionSplashScreenToMainScreen(it))
        }
    }
}