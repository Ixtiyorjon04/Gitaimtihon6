package uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.gitaimtihon6samandar.data.repository.impl.IntroRepositoryImpl
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.SplashScreenViewModel

class SplashScreenViewModelImpl : SplashScreenViewModel,ViewModel(){
    override val openMainScreenLiveData=MutableLiveData<Int>()
    override val openIntroScreenLiveData=MutableLiveData<Unit>()
    override val openSignInScreenLiveData=MutableLiveData<Unit>()
    private val repository=IntroRepositoryImpl.getInstance()
    init {

        viewModelScope.launch {
            delay(2000)
            if (repository.isFirst) {
                openIntroScreenLiveData.value = Unit
            }else{
                if (repository.id==0){
                    openSignInScreenLiveData.value=Unit
                }else{
                    openMainScreenLiveData.value=repository.id
                }

            }
        }
    }

}