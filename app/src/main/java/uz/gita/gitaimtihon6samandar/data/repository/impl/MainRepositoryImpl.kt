package uz.gita.gitaimtihon6samandar.data.repository.impl

import uz.gita.gitaimtihon6samandar.data.repository.MainRepository
import uz.gita.gitaimtihon6samandar.data.room.AppDatabase
import uz.gita.gitaimtihon6samandar.data.room.entities.GroupEntity
import uz.gita.gitaimtihon6samandar.data.room.entities.StudentEntity
import uz.gita.gitaimtihon6samandar.data.room.entities.UserEntity

class MainRepositoryImpl private constructor(): MainRepository{
    val appDatabase=AppDatabase.getAppDatabase()
    val entityDao=appDatabase.getUserDao()
    val groupDao=appDatabase.getGroupDao()
    val studentDao=appDatabase.getStudentDao()

    companion object{
        private var mainRepository:MainRepository?=null
        fun init(){
            mainRepository=MainRepositoryImpl()
        }
        fun getInstance()= mainRepository!!

    }

    override fun getAll(): List<UserEntity> {
        return entityDao.getAll()
    }

    override fun insert(entity: UserEntity) {
        entityDao.insert(entity)
    }

    override fun delete(entity: UserEntity) {
    }

    override fun update(entity: UserEntity) {

    }

    override fun getAllGroups(id: Int): List<GroupEntity> {
        return groupDao.getAll(id)
    }

    override fun insertGroup(entity: GroupEntity) {
        groupDao.insert(entity)
    }

    override fun deleteGroup(entity: GroupEntity) {
        groupDao.delete(entity)
    }

    override fun updateGroup(entity: GroupEntity) {
        groupDao.update(entity)
    }
      override fun getAllStudents(id: Int): List<StudentEntity> {
        return studentDao.getAll(id)
    }

    override fun insertStudent(entity: StudentEntity) {
        studentDao.insert(entity)
    }

    override fun deleteStudent(entity: StudentEntity) {
        studentDao.delete(entity)
    }

    override fun updateStudent(entity: StudentEntity) {
        studentDao.update(entity)
    }


}