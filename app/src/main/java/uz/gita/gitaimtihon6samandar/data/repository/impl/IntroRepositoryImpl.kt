package uz.gita.gitaimtihon6samandar.data.repository.impl

import uz.gita.gitaimtihon6samandar.data.repository.IntroRepository
import uz.gita.gitaimtihon6samandar.data.sharedpref.SharedPref

class IntroRepositoryImpl private constructor(): IntroRepository {
    companion object{
        private var introRepositoryImpl:IntroRepository?=null
        fun init(){
            introRepositoryImpl=IntroRepositoryImpl()
        }
        fun getInstance()= introRepositoryImpl!!
    }
    private var sharedPref=SharedPref.getInstance()
    override var isFirst: Boolean; get() = sharedPref.isFirst ; set(value) {sharedPref.isFirst=value}
    override var id: Int
        get() = sharedPref.id
        set(value) {sharedPref.id=value}

}