package uz.gita.gitaimtihon6samandar.presenter.viewmodel

import androidx.lifecycle.LiveData

interface IntroScreenViewModel {
    val nextPageOpenLiveData:LiveData<Int>
    val openMainScreenLiveData:LiveData<Unit>
     fun next(pos:Int)
}