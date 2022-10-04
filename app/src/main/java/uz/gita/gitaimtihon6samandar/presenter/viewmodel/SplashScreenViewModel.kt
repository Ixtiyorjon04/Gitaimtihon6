package uz.gita.gitaimtihon6samandar.presenter.viewmodel

import androidx.lifecycle.LiveData

interface SplashScreenViewModel {
    val openMainScreenLiveData:LiveData<Int>
    val openIntroScreenLiveData:LiveData<Unit>
    val openSignInScreenLiveData:LiveData<Unit>
}