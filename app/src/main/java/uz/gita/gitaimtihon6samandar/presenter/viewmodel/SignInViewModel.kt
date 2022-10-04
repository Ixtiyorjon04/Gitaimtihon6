package uz.gita.gitaimtihon6samandar.presenter.viewmodel

import androidx.lifecycle.LiveData

interface SignInViewModel {
    val loginMainScreenViewModelLiveData: LiveData<Int>
    val loginViewModelLiveData: LiveData<String>
    val signUpLiveData: LiveData<Unit>

    fun signUp()
    fun login(name:String, password:String)
}