package uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import uz.gita.gitaimtihon6samandar.data.repository.impl.MainRepositoryImpl
import uz.gita.gitaimtihon6samandar.data.room.entities.UserEntity
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.SignUpViewModel

class SignUpViewModelImpl : SignUpViewModel, ViewModel(){
    override val signInLivedata=MutableLiveData<Unit>()
    override val signErrorUpLiveData=MutableLiveData<String>()
    override val signUpViewModel=MutableLiveData<String>()
    private val repositoryImpl= MainRepositoryImpl.getInstance()
    override fun signUp(name: String, password: String, password2: String) {
        val list=repositoryImpl.getAll()
        var massage=""
        if (name.length>3){
        if (password.length>6) {
            if (password == password2) {
                if (list.size==0){
                    massage = "$name ro'yhatdan o'tdi"
                    repositoryImpl.insert(UserEntity(0, name, password))
                    signIn()
                }
                else{
                for (i in 0 until list.size) {
                    if (list[i].name != name) {
                        massage = "$name ro'yhatdan o'tdi"
                        repositoryImpl.insert(UserEntity(0, name, password))
                        signIn()
                    } else {
                        massage = "Bu nom oldindan mavjud"
                    }
                }}
            } else {
                massage = "Parol to'g'ri kiritganini tekshirib ko'ring"
            }
        }
        else{
            massage="Password 6 ta belgidan ko'p bo'lishi kerak!!"
        }
        }else{
            massage="Name 3 ta belgidan ko'p bo'lishi kerak!!"
        }
        signErrorUpLiveData.value=massage

    }

    override fun signIn() {
        signInLivedata.value=Unit
    }


}