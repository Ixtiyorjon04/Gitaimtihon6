package uz.gita.gitaimtihon6samandar.presenter.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.gitaimtihon6samandar.data.room.entities.GroupEntity

interface MainViewModel {
    fun getAll()
    var id:Int
    val logOutLiveData:LiveData<Unit>
    val addDialogOpenLiveData:LiveData<Int>
    val addLiveData:LiveData<Unit>
    val openStudentScreen:LiveData<Int>
    val getAllGroups:LiveData<List<GroupEntity>>
    val errorAddLiveData:LiveData<String>
    fun addDialogOpen(id:Int)
    fun addGroup(name:String)
    fun openStudentScreen(id:Int)
    fun logout()
    fun update(entity: GroupEntity)
    fun edit(entity: GroupEntity)
    fun delete(entity: GroupEntity)
}