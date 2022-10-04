package uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.gitaimtihon6samandar.data.repository.IntroRepository
import uz.gita.gitaimtihon6samandar.data.repository.impl.IntroRepositoryImpl
import uz.gita.gitaimtihon6samandar.data.repository.impl.MainRepositoryImpl
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.SignInViewModel

class SignInViewModelImpl : SignInViewModel,ViewModel(){
    override val loginMainScreenViewModelLiveData=MutableLiveData<Int>()
    override val loginViewModelLiveData= MutableLiveData<String>()
    override val signUpLiveData= MutableLiveData<Unit>()

    private val repositoryImpl= MainRepositoryImpl.getInstance()
    private val repositoryIntro= IntroRepositoryImpl.getInstance()
    override fun signUp() {
        signUpLiveData.value=Unit
    }

    override fun login(name: String, password:String) {

        val list=repositoryImpl.getAll()
        var massage=""
        for (i in 0 until list.size){
            if (list[i].name==name ){
                if (list[i].password==password){
                    massage="Xush kelibsiz"
                    loginMainScreenViewModelLiveData.value=list[i].id
                    repositoryIntro.id=list[i].id

                }else{
                    massage="Parol xato kiritlgan"
                }
            }else{
                massage="Name xato kiritlgan"
            }
        }
        if (list.size==0){
            massage="Ro'yhatdan o'ting"
        }
        loginViewModelLiveData.value=massage
    }
}