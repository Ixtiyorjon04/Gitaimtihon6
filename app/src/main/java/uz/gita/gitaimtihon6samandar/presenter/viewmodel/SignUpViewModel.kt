package uz.gita.gitaimtihon6samandar.presenter.viewmodel

import androidx.lifecycle.LiveData

interface SignUpViewModel {
   val signInLivedata:LiveData<Unit>
   val signErrorUpLiveData:LiveData<String>

   val signUpViewModel:LiveData<String>
   fun signUp(name:String,password:String,password2: String)
   fun signIn()
}