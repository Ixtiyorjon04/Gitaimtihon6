package uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.gitaimtihon6samandar.data.repository.impl.MainRepositoryImpl
import uz.gita.gitaimtihon6samandar.data.room.entities.GroupEntity
import uz.gita.gitaimtihon6samandar.data.room.entities.StudentEntity

import uz.gita.gitaimtihon6samandar.presenter.viewmodel.StudentViewModel

class StudentViewModelImpl : StudentViewModel,ViewModel() {
    override var id:Int=0
    private val repositoryImpl=MainRepositoryImpl.getInstance()
    override val addDialogOpenLiveData=MutableLiveData<Int>()
    override val addLiveData=MutableLiveData<Unit>()
    override val errorAddLiveData=MutableLiveData<String>()

    override val getAllStudents=MutableLiveData<List<StudentEntity>>()
    override fun getAll() {
        getAllStudents.value = repositoryImpl.getAllStudents(id)
    }
    override fun addDialogOpen(id: Int) {
        addDialogOpenLiveData.value=id
    }

    init {
        getAll()
    }
    override fun addStudent(name:String,surname:String) {

        if (name.length>0&&surname.length>0){
            errorAddLiveData.value="Student qo'shildi"
            repositoryImpl.insertStudent(StudentEntity(0,name,surname,id,repositoryImpl.getAllStudents(id).size))
            addLiveData.value=Unit
            getAll()
        }else{
            errorAddLiveData.value="Maydonlarni to'ldiring kiriting"
        }

    }
}