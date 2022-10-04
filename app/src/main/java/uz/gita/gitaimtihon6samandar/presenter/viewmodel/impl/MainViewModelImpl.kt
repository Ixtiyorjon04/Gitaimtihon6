package uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.gitaimtihon6samandar.data.repository.impl.IntroRepositoryImpl
import uz.gita.gitaimtihon6samandar.data.repository.impl.MainRepositoryImpl
import uz.gita.gitaimtihon6samandar.data.room.entities.GroupEntity
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.MainViewModel

class MainViewModelImpl : MainViewModel,ViewModel() {
    override var id:Int=0
    override val logOutLiveData=MutableLiveData<Unit>()
    private val repository= IntroRepositoryImpl.getInstance()
    private val repositoryImpl=MainRepositoryImpl.getInstance()
    override val addDialogOpenLiveData=MutableLiveData<Int>()
    override val addLiveData=MutableLiveData<Unit>()
    override val openStudentScreen=MutableLiveData<Int>()
    override val errorAddLiveData=MutableLiveData<String>()

    override val getAllGroups=MutableLiveData<List<GroupEntity>>()
    override fun getAll() {
        getAllGroups.value = repositoryImpl.getAllGroups(id)
    }
    override fun addDialogOpen(id: Int) {
        addDialogOpenLiveData.value=id
    }

    init {
        getAll()
    }
    override fun addGroup(name:String) {

        if (name.length>0){
            errorAddLiveData.value="Gruh qo'shildi"
            repositoryImpl.insertGroup(GroupEntity(0,name,repositoryImpl.getAllGroups(id).size,id))
            addLiveData.value=Unit
            getAll()
        }else{
            errorAddLiveData.value="Nom kiriting"
        }

    }

    override fun openStudentScreen(id:Int) {
       openStudentScreen.value=id
    }

    override fun logout() {
        repository.id=0
        logOutLiveData.value=Unit
    }

    override fun update(entity: GroupEntity) {
        repositoryImpl.updateGroup(entity)
        getAll()
    }

    override fun edit(entity: GroupEntity) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: GroupEntity) {
        TODO("Not yet implemented")
    }
}