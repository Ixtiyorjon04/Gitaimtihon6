package uz.gita.gitaimtihon6samandar.data.repository

import androidx.lifecycle.LiveData
import uz.gita.gitaimtihon6samandar.data.room.entities.GroupEntity
import uz.gita.gitaimtihon6samandar.data.room.entities.StudentEntity
import uz.gita.gitaimtihon6samandar.data.room.entities.UserEntity

interface MainRepository {

    fun  getAll():List<UserEntity>

    fun insert(entity: UserEntity)
    fun delete(entity: UserEntity)
    fun update(entity: UserEntity)



    fun getAllGroups(id:Int):List<GroupEntity>
    fun insertGroup(entity: GroupEntity)
    fun deleteGroup(entity: GroupEntity)
    fun updateGroup(entity: GroupEntity)

     fun getAllStudents(id:Int):List<StudentEntity>
    fun insertStudent(entity: StudentEntity)
    fun deleteStudent(entity: StudentEntity)
    fun updateStudent(entity: StudentEntity)


}