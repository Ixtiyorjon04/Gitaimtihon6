package uz.gita.gitaimtihon6samandar.presenter.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.gitaimtihon6samandar.data.room.entities.GroupEntity
import uz.gita.gitaimtihon6samandar.data.room.entities.StudentEntity

interface StudentViewModel {
    fun getAll()
    var id:Int
    val addDialogOpenLiveData:LiveData<Int>
    val addLiveData:LiveData<Unit>
    val getAllStudents:LiveData<List<StudentEntity>>
    val errorAddLiveData:LiveData<String>
    fun addDialogOpen(id:Int)
    fun addStudent(name:String,surname:String)
}