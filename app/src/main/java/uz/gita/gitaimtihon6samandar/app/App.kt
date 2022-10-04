package uz.gita.gitaimtihon6samandar.app

import android.app.Application
import uz.gita.gitaimtihon6samandar.data.repository.impl.IntroRepositoryImpl
import uz.gita.gitaimtihon6samandar.data.repository.impl.MainRepositoryImpl
import uz.gita.gitaimtihon6samandar.data.room.AppDatabase
import uz.gita.gitaimtihon6samandar.data.sharedpref.SharedPref

class App:Application (){
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
        IntroRepositoryImpl.init()
        AppDatabase.init(this)
        MainRepositoryImpl.init()
    }
}